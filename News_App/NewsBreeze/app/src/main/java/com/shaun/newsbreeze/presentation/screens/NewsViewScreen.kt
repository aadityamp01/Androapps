package com.shaun.newsbreeze.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shaun.newsbreeze.localdatabase.ArticleLocal
import com.shaun.newsbreeze.models.Article
import com.shaun.newsbreeze.presentation.components.AuthorRow
import com.shaun.newsbreeze.presentation.components.NewsViewToolbar
import com.shaun.newsbreeze.presentation.components.NewsViewTopSection
import com.shaun.newsbreeze.viewmodels.HomeViewModel

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun NewsViewScreen(
    article: Article?,
    onBackPressed: () -> Unit,
    onSaveClicked: (Article?) -> Unit,
    homeViewModel: HomeViewModel
) {
    val savedArticles: List<ArticleLocal>? by homeViewModel.savedArticles.observeAsState()

    Column(Modifier.fillMaxSize()) {


        Box() {
            NewsViewTopSection(
                imageUrl = article?.urlToImage.toString()
            )

            LazyColumn() {

                stickyHeader {
                    Column {
                        Spacer(modifier = Modifier.height(20.dp))
                        NewsViewToolbar(onSaveClick = {
                            onSaveClicked(article)
                        }, isSaved = savedArticles?.none {
                            it.title == article?.title
                        } == false, onBackClick = {
                            onBackPressed()
                        }, onDelete = {
                            homeViewModel.deleteArticle(article?.title.toString())
                        })
                    }
                }

                item {
                    Column {
                        Spacer(
                            modifier = Modifier
                                .height(280.dp)
                        )
                        Card(
                            shape = RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp),
                            backgroundColor = Color.White,
                            modifier = Modifier.fillMaxSize()
                        ) {

                            AuthorRow(
                                author = article?.author ?: "null",
                                source = article?.source?.name ?: "null",
                                imageUrl = article?.urlToImage ?: "",
                                isSaved = (savedArticles?.none {
                                    it.title == article?.title
                                } == false), onSaveClick = {
                                    onSaveClicked(article)
                                }) {
                                homeViewModel.deleteArticle(articleLocal = article?.title.toString())
                            }

                        }
                    }
                }



                item {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(20.dp)
                    ) {
                        Text(
                            text = article?.title.toString(),
                            fontSize = 35.sp,
                            fontFamily = FontFamily.Serif
                        )

                        Text(
                            text = article?.content.toString(),
                            fontSize = 25.sp,
                            fontFamily = FontFamily.Serif
                        )

                    }
                }
            }

        }
    }
}