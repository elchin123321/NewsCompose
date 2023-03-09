package com.example.newscompose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newscompose.ui.screens.StoriesViewModel

@Composable
fun NewsApp(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val storiesViewModel:StoriesViewModel = viewModel()
    }
}