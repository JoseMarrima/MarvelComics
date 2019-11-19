package com.josemarrima.marvelcomics.data

import androidx.lifecycle.LiveData
import com.josemarrima.marvelcomics.data.local.Comic
import com.josemarrima.marvelcomics.data.local.ComicDao
import com.josemarrima.marvelcomics.data.local.asDomainModel
import com.josemarrima.marvelcomics.data.remote.ComicService
import com.josemarrima.marvelcomics.model.MarvelResponse
import com.josemarrima.marvelcomics.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Singleton

@Singleton
class ComicRepository(val comicDao: ComicDao,
                      val comicService: ComicService,
                      val responseHandler: ResponseHandler) {

    private suspend fun getMarvelResponse(): Resource<MarvelResponse> {
        return try {
            val marvelResponse = comicService.getAsyncComicResponse(TS, API_KEY, HASH)
            return responseHandler.handleSuccess(marvelResponse)
        } catch (e: HttpException) {
            responseHandler.handleException(e)
        }
    }

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh videos is called")
            val marvelResponse = getMarvelResponse()
            if (marvelResponse.status == Resource.Status.SUCCESS) {
                comicDao.insertListofComics(marvelResponse.data!!.asDomainModel())
            }

        }
    }

    fun getListOfComics(): LiveData<List<Comic>> {
        return comicDao.getAllComics()
    }
}