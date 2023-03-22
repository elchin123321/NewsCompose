package com.example.newscompose.ui.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.newscompose.R


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    url: String,
    modifier: Modifier = Modifier
) {
    val backEnabled = remember { mutableStateOf(false) }
    var webView: WebView? = null
    val errorHandler = remember { mutableStateOf(false) }

    val loaderDialogScreen = remember { mutableStateOf(false) }

    if (errorHandler.value) {
        ErrorScreen(retryClick = {errorHandler.value = false})
    } else {
        Box(
            contentAlignment = Alignment.TopCenter
        ) {

            AndroidView(
                factory = {
                    WebView(it).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )

                        settings.javaScriptEnabled = true
                        webViewClient = object : WebViewClient() {
                            override fun onLoadResource(view: WebView?, url: String?) {
                                super.onLoadResource(view, url)
                                loadUrl(
                                    "javascript:(window.onload =function() {" +
                                            "document.getElementsByClassName('NYTAppHideMasthead css-1q2w90k e1m0pzr40')[0].style.display='none';" +
                                            "document.getElementById('site-index').style.display='none';" +
                                            "document.getElementsByClassName('css-1e1s8k7 dockVisible')[0].style.display='none';" +
                                            "})()"
                                )

                            }

                            override fun onPageStarted(
                                view: WebView,
                                url: String?,
                                favicon: Bitmap?
                            ) {
                                super.onPageStarted(view, url, favicon)
                                backEnabled.value = view.canGoBack()
                                loaderDialogScreen.value = true
                            }

                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                                loaderDialogScreen.value = false
                            }

                            override fun onReceivedError(
                                view: WebView?,
                                request: WebResourceRequest?,
                                error: WebResourceError?
                            ) {
                                try {
                                    webView?.stopLoading()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                                super.onReceivedError(view, request, error)
                                errorHandler.value = true
                            }

                        }
                        loadUrl(url)
                        webView = this
                    }
                },
                update = {
                    webView = it

                }
            )
            if (loaderDialogScreen.value) {
                Card(
                    elevation = 4.dp,
                    modifier = Modifier.padding(4.dp),
                    shape = RoundedCornerShape(32.dp)
                ) {
                    CircularProgressIndicator(
                        Modifier
                            .padding(12.dp)
                            .size(20.dp),
                        color = Color.Gray,
                        strokeWidth = 2.dp
                    )
                }
            }

            BackHandler(enabled = backEnabled.value) {
                webView?.goBack()
            }
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
