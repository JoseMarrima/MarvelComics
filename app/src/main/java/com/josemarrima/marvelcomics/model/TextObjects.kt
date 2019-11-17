package com.josemarrima.marvelcomics.model

import com.google.gson.annotations.SerializedName

data class TextObjects (

	@SerializedName("type") val type : String,
	@SerializedName("language") val language : String,
	@SerializedName("text") val text : String
)