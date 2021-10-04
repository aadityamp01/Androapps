package com.shaun.newsbreeze.presentation.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shaun.newsbreeze.localdatabase.ArticleLocal
import com.shaun.newsbreeze.models.Article
import com.shaun.newsbreeze.presentation.components.*
import com.shaun.newsbreeze.ui.theme.BackgroundColorBreeze
import com.shaun.newsbreeze.viewmodels.HomeViewModel
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onReadClicked: (Article) -> Unit,
    onSaveClicked: (Article) -> Unit,
    onDeleteClicked: (Article) -> Unit,
    openSave: () -> Unit
) {

    val topHeadlines: List<Article>? by homeViewModel.newsArticles.observeAsState(listOf())

    val savedArticles: List<ArticleLocal>? by homeViewModel.savedArticles.observeAsState()
    val noItemsFound: Boolean by homeViewModel.searchFailed.observeAsState(false)

    val isLoading: Boolean by homeViewModel.isInSearchMode.observeAsState(initial = true)
    homeViewModel.searchFailed.observeForever {
        Log.d("TAG", "HomeScreen: $it")
        homeViewModel.isInSearchMode.postValue(!it)

    }

    homeViewModel.newsArticles.observeForever {
        if (it?.isNotEmpty() == true) {
            homeViewModel.isInSearchMode.postValue(false)
        }
    }
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var currentSort by remember {
        mutableStateOf("Date")
    }
    ModalBottomSheetLayout(
        sheetContent = {
            LibraryBottomSheet(state, scope, currentSort, onSortItemClicked = {
                currentSort = it

                homeViewModel.sort(it)
            })
        },
        sheetState = state,
        scrimColor = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
    )
    {

        Column(
            Modifier
                .fillMaxSize()
                .background(BackgroundColorBreeze)
        ) {


            Spacer(modifier = Modifier.height(50.dp))
            Header(openSave = {
                openSave()
            })
            Spacer(modifier = Modifier.height(10.dp))

            SearchBar(homeViewModel, onSort = {
                scope.launch {
                    state.show()
                }
            }) {
                Log.d("TAG", "HomeScreen: $it")
                homeViewModel.searchNews(it)
            }
            Spacer(modifier = Modifier.height(20.dp))


            Box {
                LazyColumn() {
                    topHeadlines?.let {
                        itemsIndexed(it) { index, item ->

                            EnterAnimation {
                                HomeNewsItem(
                                    title = item.title,
                                    shortDescription = item.description,
                                    imageUrl = item.urlToImage,
                                    date = item.publishedAt.substring(0, 10),
                                    onReadClick = {
                                        onReadClicked(item)
                                    },
                                    onSaveClick = {
                                        onSaveClicked(item)
                                    },
                                    isSaved = savedArticles?.none { articleLocal ->
                                        articleLocal.title == item.title
                                    } == false,
                                    onDelete = {
                                        onDeleteClicked(item)
                                    }
                                )
                            }

                        }

                    }
                }

                if (isLoading) {
                    Column {
                        repeat(4) {
                            EnterAnimation {
                                ShimmerItem()
                            }

                        }
                    }

                }

                if (noItemsFound) {
                    NotFoundScreen()
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
            initialOffsetY = { -100 }
        ),
        exit = slideOutVertically(
            targetOffsetY = { 100 }
        ),
        content = content,
        initiallyVisible = false
    )

}