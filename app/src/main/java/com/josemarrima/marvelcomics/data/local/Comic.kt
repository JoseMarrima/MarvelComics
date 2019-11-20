package com.josemarrima.marvelcomics.data.local


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comic(@PrimaryKey
                 val id: Int?,
                 val title: String?,
                 val description: String?,
                 val url: String?)

