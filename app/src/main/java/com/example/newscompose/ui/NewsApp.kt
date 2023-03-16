package com.example.newscompose.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newscompose.R
import com.example.newscompose.ui.screens.home.HomeScreen
import com.example.newscompose.ui.screens.home.StoriesViewModel
import com.example.newscompose.ui.screens.sections.SectionsScreen


enum class Screens(
    val title: String = "",
    val route: String = "",
    @DrawableRes val icon: Int = R.drawable.new_york_times_t_icon
) {
    Home("Today", "today", R.drawable.new_york_times_t_icon),
    Sections("Sections", "sections", R.drawable.selections)
}

@Composable
fun NewsApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val storiesViewModel: StoriesViewModel = viewModel()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                Screens.values().forEach { screen ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    BottomNavigationItem(
                        selected = selected,
                        onClick = { navController.navigate(screen.route) },
                        icon = {
                            Icon(
                                painterResource(id = screen.icon),
                                contentDescription = screen.title
                            )
                        },
                        label = { Text(screen.title) }
                    )
                }
            }
        }
    )
    {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {


            NavHost(startDestination = "today", navController = navController) {
                composable("today") {
                    HomeScreen(
                        modifier = Modifier.padding(4.dp),
                        storiesUiState = storiesViewModel.storiesUiState,
                        onArticleClick = {
                            navController.navigate("webview")
                        }
                    )
                }
                composable("sections") {
                    SectionsScreen(
                        onBackPressed = { /*TODO*/ },
                        onSectionClick = { /*TODO*/ },
                    )
                }
                composable("webview") {
                    //TODO
                }
            }


        }
    }
}

