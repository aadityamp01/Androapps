package com.shaun.newsbreeze.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shaun.newsbreeze.localdatabase.ArticleCache
import com.shaun.newsbreeze.localdatabase.ArticleDao
import com.shaun.newsbreeze.localdatabase.ArticleLocal
import com.shaun.newsbreeze.models.NewsArticles
import com.shaun.newsbreeze.network.NewsApiService

class HomeScreenRepositoryImpl(
    val apiKey: String,
    val retrofit: NewsApiService,
    private val articleDao: ArticleDao,
    private val hasInternet: Boolean,
) : HomeScreenRepository {

    override var searchFailed = MutableLiveData(false)


    override suspend fun getHeadlines(): NewsArticles {

        return retrofit.getTopHeadlines(apiKey = apiKey)

    }

    override suspend fun getCache(): List<ArticleCache> {

        return articleDao.observeArticleCache()


    }

    override suspend fun searchArticle(query: String): NewsArticles {
        return retrofit.searchArticles(query = query, apiKey = apiKey)
    }

    override fun getArticle(): LiveData<List<ArticleLocal>> {
        return articleDao.observeArticles()
    }

    override suspend fun deleteArticle(article: String) {
        articleDao.deleteArticle(article)
    }

    override suspend fun insertArticle(article: ArticleLocal) {
        articleDao.insertArticle(article)
    }

    companion object {
        private const val TAG = "HomeScreenRepositoryImp"
    }
}