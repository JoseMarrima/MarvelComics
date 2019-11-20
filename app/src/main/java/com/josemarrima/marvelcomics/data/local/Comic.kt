package com.josemarrima.marvelcomics.data.local


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Comic(@PrimaryKey
                 val id: Int,
                 val title: String?,
                 val description: String?,
                 val url: String?): Parcelable