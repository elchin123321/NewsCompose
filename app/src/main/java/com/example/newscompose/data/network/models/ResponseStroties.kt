package com.example.newscompose.data.network.models

import com.google.gson.annotations.SerializedName

data class ResponseStroties(
    @SerializedName("status")
    val status:String,
    @SerializedName("section")
    val section:String,
    @SerializedName("last_updated")
    val last_updated:String,
    @SerializedName("results")
    val results:List<Article>
)
