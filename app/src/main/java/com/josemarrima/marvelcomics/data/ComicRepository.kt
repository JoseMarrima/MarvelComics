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
import java.lang.Exception
import javax.inject.Singleton

@Singleton
class ComicRepository(val comicDao: ComicDao,
                      val comicService: ComicService,
                      val responseHandler: ResponseHandler) {

//    private suspend fun getMarvelResponse(): Resource<MarvelResponse> {
//        return try {
//            val marvelResponse = comicService.getAsyncComicResponse(TS, API_KEY, HASH)
//            Timber.d("Response from network ${marvelResponse.attributionText}")
//            return responseHandler.handleSuccess(marvelResponse)
//        } catch (e: HttpException) {
//            responseHandler.handleException(e)
//        }

    suspend fun cacheData() {
        withContext(Dispatchers.IO) {
            val marvelResponse: Resource<MarvelResponse>
            Timber.d("cache data is called")
            marvelResponse = try {
                val marvel = comicService.getAsyncComicResponse(TS, API_KEY, HASH)
                Timber.d("Response from network ${marvel.attributionText}")
                responseHandler.handleSuccess(marvel)
            } catch (e: Exception) {
                responseHandler.handleException(e)
            }

            Timber.d("Status is ${marvelResponse.status}")
            Timber.d("Error is ${marvelResponse.message}")
            if (marvelResponse.status == Resource.Status.SUCCESS) {
                comicDao.insertListofComics(marvelResponse.data!!.asDomainModel())
            }

        }
    }

    fun getListOfComics(): LiveData<List<Comic>> {
        return comicDao.getAllComics()
    }
}