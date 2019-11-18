package com.josemarrima.marvelcomics.data

import com.josemarrima.marvelcomics.data.local.ComicDao
import com.josemarrima.marvelcomics.data.local.asDomainModel
import com.josemarrima.marvelcomics.data.remote.ComicService
import com.josemarrima.marvelcomics.model.MarvelResponse
import com.josemarrima.marvelcomics.util.*
import retrofit2.HttpException
import javax.inject.Singleton

@Singleton
class ComicRepository(val comicDao: ComicDao,
                      val comicService: ComicService,
                      val responseHandler: ResponseHandler) {

    suspend fun getMarvelResponse(): Resource<MarvelResponse> {
        return try {
            val marvelResponse = comicService.getAsyncComicResponse(TS, API_KEY, HASH)
            comicDao.insertListofComics(marvelResponse.asDomainModel())
            return responseHandler.handleSuccess(marvelResponse)
        } catch (e: HttpException) {
            responseHandler.handleException(e)
        }
    }
}