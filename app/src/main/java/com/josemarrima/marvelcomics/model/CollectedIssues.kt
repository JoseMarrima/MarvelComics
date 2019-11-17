package com.josemarrima.marvelcomics.model

import com.google.gson.annotations.SerializedName

data class CollectedIssues(@SerializedName("resourceURI") val resourceURI : String,
                           @SerializedName("name") val name : String)
