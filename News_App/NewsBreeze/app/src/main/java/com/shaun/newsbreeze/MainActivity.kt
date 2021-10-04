package com.shaun.newsbreeze

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shaun.newsbreeze.localdatabase.toArticleLocal
import com.shaun.newsbreeze.models.Article
import com.shaun.newsbreeze.navigation.Routes
import com.shaun.newsbreeze.presentation.screens.HomeScreen
import com.shaun.newsbreeze.presentation.screens.NewsViewScreen
import com.shaun.newsbreeze.presentation.screens.SavedArticles
import com.shaun.newsbreeze.presentation.screens.SplashScreen
import com.shaun.newsbreeze.ui.theme.NewsBreezeTheme
import com.shaun.newsbreeze.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_NewsBreeze)

        setContent {


            val homeViewModel: HomeViewModel = viewModel()
            NewsBreezeTheme(darkTheme = false) {
                Surface(color = MaterialTheme.colors.background) {


                    val navController = rememberNavController()

                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {


                        NavHost(
                            navController = navController,
                            startDestination = Routes.HomeScreen.route
                        ) {

                            composable(Routes.SplashScreen.route) {

                                SplashScreen(navController, homeViewModel)
                            }

                            composable(Routes.HomeScreen.route) {
                                HomeScreen(homeViewModel, onReadClicked = { article ->
                                    navController.currentBackStackEntry?.arguments =
                                        Bundle().apply {
                                            putSerializable("article", article)
                                        }
                                    navController.navigate(Routes.NewsViewScreen.route)

                                }, onSaveClicked = { item ->
                                    homeViewModel.insertArticle(
                                        articleLocal = item.toArticleLocal()
                                    )
                                }, onDeleteClicked = {
                                    Toast.makeText(this@MainActivity, "Unsaved", Toast.LENGTH_SHORT)
                                        .show()
                                    homeViewModel.deleteArticle(it.title)
                                }, openSave = {
                                    navController.navigate(Routes.SavedScreen.route)
                                })
                            }

                            composable(Routes.NewsViewScreen.route) {

                                val article =
                                    navController.previousBackStackEntry?.arguments?.getSerializable(
                                        "article"
                                    ) as Article

                                Log.d("TAG", "onCreate: $article")
                                EnterAnimation {
                                    NewsViewScreen(article, onBackPressed = {
                                        navController.popBackStack()
                                    }, onSaveClicked = { item ->
                                        homeViewModel.insertArticle(
                                            articleLocal = item.toArticleLocal()
                                        )
                                    }, homeViewModel)
                                }
                            }

                            composable(Routes.SavedScreen.route) {
                                EnterAnimation {
                                    SavedArticles(homeViewModel, onBackClicked = {
                                        navController.popBackStack()
                                    }) { article ->
                                        navController.currentBackStackEntry?.arguments =
                                            Bundle().apply {
                                                putSerializable("article", article)
                                            }
                                        navController.navigate(Routes.NewsViewScreen.route)

                                    }
                                }
                            }
                        }
                    }

                }
            }
        }


    }
}

@ExperimentalAnimationApi
@Composable
fun EnterAnimation(content: @Composable () -> Unit) {


    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { 1000 }
        ),
        exit = slideOutVertically(
//            targetOffsetY = { 1000 }
        ),
        content = content,
        initiallyVisible = false
    )

}