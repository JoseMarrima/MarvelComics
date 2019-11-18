package com.josemarrima.marvelcomics.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.josemarrima.marvelcomics.data.ComicRepository
import com.josemarrima.marvelcomics.data.local.ComicDao
import com.josemarrima.marvelcomics.data.local.ComicDatabase
import com.josemarrima.marvelcomics.data.remote.ComicService
import com.josemarrima.marvelcomics.util.DATABASE_NAME
import com.josemarrima.marvelcomics.util.ResponseHandler
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideComicDao(comicDatabase: ComicDatabase): ComicDao {
        return comicDatabase.comicDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideComicDatabase(context: Context): ComicDatabase {
        return Room.databaseBuilder(context,
            ComicDatabase::class.java, DATABASE_NAME).build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideComicService(): ComicService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("https://gateway.marvel.com/v1/public/")
            .build()
            .create(ComicService::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideComicRepository(comicDao: ComicDao,
                               comicService: ComicService,
                               responseHandler: ResponseHandler): ComicRepository {

        return ComicRepository(comicDao, comicService, responseHandler)
    }
}