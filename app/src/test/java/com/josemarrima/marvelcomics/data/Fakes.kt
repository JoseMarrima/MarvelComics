package com.josemarrima.marvelcomics.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.josemarrima.marvelcomics.data.local.Comic
import com.josemarrima.marvelcomics.data.local.ComicDao
import com.josemarrima.marvelcomics.data.remote.ComicService
import com.josemarrima.marvelcomics.model.MarvelResponse
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

class ComicDaoFake(listComic: List<Comic>) : ComicDao {
    /**
     * A channel is a Coroutines based implementation of a blocking queue.
     *
     * We're using it here as a buffer of inserted elements.
     *
     * This uses a channel instead of a list to allow multiple threads to call insertComic and
     * synchronize the results with the test thread.
     */
    private val insertedForNext = Channel<List<Comic>>(capacity = Channel.BUFFERED)

    override suspend fun insertListofComics(listOfComics: List<Comic>) {
        insertedForNext.send(listOfComics)
        _comicLiveData.value = listOfComics
    }

    override fun getAllComics(): LiveData<List<Comic>> {
        return _comicLiveData
    }

    private val _comicLiveData = MutableLiveData<List<Comic>>(listComic)
}


/**
 * Testing Fake implementation of ComicService
 */
class ComicServiceFake(var result: MarvelResponse) : ComicService {
    override suspend fun getAsyncComicResponse(
        ts: String,
        apikey: String,
        hash: String
    ) = result
}

/**
 * Testing Fake for ComicService that lets you complete or error all current requests
 */
class ComicServiceCompletableFake(): ComicService {

    private var completable = CompletableDeferred<MarvelResponse>()

    fun sendCompletionToAllCurrentRequests(result: MarvelResponse) {
        completable.complete(result)
        completable = CompletableDeferred()
    }
    fun sendErrorToCurrentRequests(throwable: Throwable) {
        completable.completeExceptionally(throwable)
        completable = CompletableDeferred()
    }

    override suspend fun getAsyncComicResponse(
        ts: String,
        apikey: String,
        hash: String
    ): MarvelResponse = completable.await()

}