package com.josemarrima.marvelcomics.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ComicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListofComics(listOfComics: List<Comic>)

    @Query("SELECT * from comic")
    fun getAllComics(): LiveData<List<Comic>>
}