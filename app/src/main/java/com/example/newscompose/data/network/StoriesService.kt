package com.example.newscompose.data.network

import com.example.newscompose.data.network.models.ResponseStroties
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoriesService {
    @GET("{section}.json")
    suspend fun fetchStories(
        @Path("section") section:String = "home",
        @Query("api-key") api:String = api_key
    ) : ResponseStroties

    companion object{
        const val api_key = "dny482tDZSIdyeYxz4AmB4okICGKDIsz"
    }
}