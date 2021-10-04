package com.shaun.newsbreeze.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shaun.newsbreeze.localdatabase.ArticleCache
import com.shaun.newsbreeze.localdatabase.ArticleLocal
import com.shaun.newsbreeze.models.NewsArticles

interface HomeScreenRepository {

    var searchFailed: MutableLiveData<Boolean>
    suspend fun getHeadlines(): NewsArticles
    suspend fun searchArticle(query: String): NewsArticles

    fun getArticle(): LiveData<List<ArticleLocal>>
    suspend fun deleteArticle(article: String)
    suspend fun insertArticle(article: ArticleLocal)

    suspend fun getCache(): List<ArticleCache>
}