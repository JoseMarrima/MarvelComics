package com.josemarrima.marvelcomics.model

import com.google.gson.annotations.SerializedName

data class Images(@SerializedName("path") val type : String,
                  @SerializedName("extension") val language : String)