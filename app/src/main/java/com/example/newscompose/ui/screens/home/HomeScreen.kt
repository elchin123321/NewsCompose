package com.example.newscompose.ui.screens.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newscompose.R
import com.example.newscompose.data.network.models.Article


@Composable
fun HomeScreen(
    storiesUiState: StoriesUiState,
    onArticleClick: (url: String) -> Unit,
    retryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Crossfade(
        targetState = storiesUiState,
        animationSpec = tween(1000)
    ) { state ->
        when (state) {
            is StoriesUiState.Loading -> LoadingHomeScreen()
            is StoriesUiState.Success -> ArticlesListScreen(
                articles = state.stories,
                onArticleClick = onArticleClick
            )
            is StoriesUiState.Error -> ErrorScreen(retryClick = retryClick)
        }
    }
}

@Composable
fun LoadingHomeScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            Modifier.align(Alignment.Center),
            color = Color.Black
        )
    }
}

@Composable
fun ArticleCard(
    article: Article,
    onArticleClick: (url: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onArticleClick(article.url) },
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = article.title,
            style = MaterialTheme.typography.h2
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = article.abstract,
            style = MaterialTheme.typography.body1

        )
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(article.multimedia[1].url)
                .crossfade(true)
                .build(),
            contentDescription = article.multimedia[1].caption,
            contentScale = ContentScale.FillWidth
        )
        Divider(modifier = Modifier.padding(top = 16.dp), color = Color.Black, thickness = 1.dp)
    }
}

@Composable
fun ArticlesListScreen(
    articles: List<Article>,
    onArticleClick: (url: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
    ) {
        items(
            items = articles,
            key = { article ->
                article.title
            }
        ) { article ->
            ArticleCard(article = article, onArticleClick)

        }
    }

}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    retryClick: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.devise_is_offline),
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = retryClick
        ) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}