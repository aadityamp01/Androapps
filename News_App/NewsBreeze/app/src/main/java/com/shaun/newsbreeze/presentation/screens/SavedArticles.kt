package com.shaun.newsbreeze.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shaun.newsbreeze.localdatabase.ArticleLocal
import com.shaun.newsbreeze.models.Article
import com.shaun.newsbreeze.presentation.components.*
import com.shaun.newsbreeze.ui.theme.BackgroundColorBreeze
import com.shaun.newsbreeze.viewmodels.HomeViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun SavedArticles(
    viewModel: HomeViewModel,
    onBackClicked: () -> Unit,
    onArticleClicked: (Article) -> Unit
) {


    val data: List<ArticleLocal?> by viewModel.savedArticles.observeAsState(initial = listOf())

    var filter by remember {
        mutableStateOf(true)
    }

    Column(Modifier.background(BackgroundColorBreeze)) {

        Spacer(modifier = Modifier.height(50.dp))
        SavedToolbar(onBackClick = {
            onBackClicked()
        })
        Spacer(modifier = Modifier.height(20.dp))
        SearchBar(viewModel, onSort = {

        }) {
            viewModel.searchNews(it)
            onBackClicked()
        }
        HeaderSavedScreen(time = if (filter) "Today" else "All") {
            filter = false
        }
        Column(
            Modifier
                .fillMaxHeight()
                .padding(30.dp)
        ) {
            Card(backgroundColor = Color.White, shape = RoundedCornerShape(20.dp)) {
                LazyColumn {
                    items(data.reversed()) {
                        it?.let {

                            if (getDate(it.publishedAt?.substring(0, 10)) || !filter) {
                                SavedItem(articleLocal = it, onClick = {
                                    onArticleClicked(it)
                                })
                            }
                        }
                    }
                }
            }
        }

    }


}


fun getDate(dateString: String?): Boolean {


    val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val date = Date()

    val todayDate = dateFormat.format(date)

    return todayDate == dateString
}

