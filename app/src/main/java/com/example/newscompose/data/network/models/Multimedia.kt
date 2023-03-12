package com.example.newscompose.data.network.models

import com.google.gson.annotations.SerializedName

data class Multimedia(
    @SerializedName("url")
    val url:String,
    @SerializedName("format")
    val format:String,
    @SerializedName("caption")
    val caption:String,
)
