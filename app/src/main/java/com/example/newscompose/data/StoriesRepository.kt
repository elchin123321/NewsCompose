package com.example.newscompose.data

import com.example.newscompose.data.network.NetworkStoriesDataSource
import com.example.newscompose.data.network.models.ResponseStroties
import javax.inject.Inject

interface StoriesRepository {
    suspend fun fetchStories(section: String): ResponseStroties
    class Base @Inject constructor(private val networkStoriesDataSource: NetworkStoriesDataSource):StoriesRepository {
        override suspend fun fetchStories(section: String): ResponseStroties =
            networkStoriesDataSource.fetchStories(section)
    }
}