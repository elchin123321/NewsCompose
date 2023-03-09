package com.example.newscompose.di

import com.example.newscompose.data.StoriesRepository
import com.example.newscompose.data.network.NetworkStoriesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppBindModule {

    @Binds
    fun bindNetworkStoriesDataSource(
        dataSource:NetworkStoriesDataSource.Base
    ):NetworkStoriesDataSource

    @Binds
    fun bindStoriesRepository(
        repository: StoriesRepository.Base
    ):StoriesRepository
}