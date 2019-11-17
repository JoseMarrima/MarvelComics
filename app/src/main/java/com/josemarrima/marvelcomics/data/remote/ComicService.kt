package com.josemarrima.marvelcomics.data.remote

import com.josemarrima.marvelcomics.model.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicService {
    @GET("comics")
    suspend fun getAsyncComicResponse(@Query("ts") ts: String,
                                      @Query("apikey") apikey: String,
                                      @Query("hash") hash: String) : MarvelResponse
}