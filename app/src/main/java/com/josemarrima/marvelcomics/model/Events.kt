package com.josemarrima.marvelcomics.model

import com.google.gson.annotations.SerializedName

data class Events (

	@SerializedName("available") val available : Int,
	@SerializedName("collectionURI") val collectionURI : String,
	@SerializedName("items") val items : List<String>,
	@SerializedName("returned") val returned : Int
)