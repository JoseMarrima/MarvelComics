package com.josemarrima.marvelcomics.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Comic::class], version = 1, exportSchema = false)
abstract class ComicDatabase : RoomDatabase() {
    abstract fun comicDao() : ComicDao
}