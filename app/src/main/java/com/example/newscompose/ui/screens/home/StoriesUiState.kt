package com.example.newscompose.ui.screens.home

import com.example.newscompose.data.network.models.Article

sealed interface StoriesUiState{
    data class Success(val stories:List<Article>): StoriesUiState
    object Error: StoriesUiState
    object Loading: StoriesUiState
}