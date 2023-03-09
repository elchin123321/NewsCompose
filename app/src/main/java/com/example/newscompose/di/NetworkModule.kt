package com.example.newscompose.di

import com.example.newscompose.data.network.StoriesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideStoriesService():StoriesService{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/topstories/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(StoriesService::class.java)
    }
}