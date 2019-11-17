package com.josemarrima.marvelcomics.data

import com.josemarrima.marvelcomics.data.local.ComicDao
import com.josemarrima.marvelcomics.data.remote.ComicService
import com.josemarrima.marvelcomics.model.MarvelResponse
import com.josemarrima.marvelcomics.util.ResponseHandler
import javax.inject.Singleton

@Singleton
class ComicRepository(val comicDao: ComicDao,
                      val comicService: ComicService,
                      val responseHandler: ResponseHandler) {

    suspend fun getMarvelResponse(): MarvelResponse {
        val marvelResponse = comicService.getAsyncComicResponse()
    }
}