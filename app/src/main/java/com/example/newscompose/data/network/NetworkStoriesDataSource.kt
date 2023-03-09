package com.example.newscompose.data.network

import com.example.newscompose.data.network.models.ResponseStroties
import javax.inject.Inject

interface NetworkStoriesDataSource {
    suspend fun fetchStories(section: String):ResponseStroties

    class Base @Inject constructor(private val service: StoriesService):NetworkStoriesDataSource {
        override suspend fun fetchStories(section:String):ResponseStroties =
            service.fetchStories(section)
    }
}