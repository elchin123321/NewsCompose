package com.example.newscompose.ui.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.data.StoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class StoriesViewModel @Inject constructor(private val repository: StoriesRepository) :
    ViewModel() {

    var storiesUiState: StoriesUiState by mutableStateOf(StoriesUiState.Loading)
        private set

    init {
        fetchStories()
    }

    fun fetchStories(section: String = "arts") {
        viewModelScope.launch {
            storiesUiState = StoriesUiState.Loading
            withContext(Dispatchers.IO) {
                storiesUiState = try {
                    StoriesUiState.Success(repository.fetchStories(section).results)
                } catch (e: IOException) {
                    Log.e("Error1", e.toString())
                    StoriesUiState.Error
                } catch (e: HttpException) {
                    Log.e("Error2", e.toString())
                    StoriesUiState.Error
                }
            }
        }
    }

}
