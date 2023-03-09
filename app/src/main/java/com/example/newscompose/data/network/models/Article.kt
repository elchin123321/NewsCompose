package com.example.newscompose.data.network.models

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("title")
    val title:String,
    @SerializedName("abstract")
    val abstract:String,
    @SerializedName("byline")
    val byLine:String,
    @SerializedName("published_date")
    val published:String,
    @SerializedName("multimedia")
    val multimedia:List<Multimedia>,
    @SerializedName("url")
    val url:String,
)
