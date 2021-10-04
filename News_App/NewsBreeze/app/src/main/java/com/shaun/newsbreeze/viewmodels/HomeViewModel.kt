package com.shaun.newsbreeze.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaun.newsbreeze.localdatabase.ArticleCache
import com.shaun.newsbreeze.localdatabase.ArticleLocal
import com.shaun.newsbreeze.localdatabase.toArticle
import com.shaun.newsbreeze.models.Article
import com.shaun.newsbreeze.models.NewsArticles
import com.shaun.newsbreeze.repository.HomeScreenRepository
import com.shaun.newsbreeze.utils.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeScreenRepository,
    private val hasInternet: Boolean
) : ViewModel() {

    val searchStringLiveData = MutableLiveData("")
    val savedSearchStringLiveData = MutableLiveData("")


    val savedArticles = repository.getArticle()
    val newsArticles = MutableLiveData<List<Article>>()


    val isInSearchMode = MutableLiveData(true)

    val searchFailed = repository.searchFailed

    init {
        viewModelScope.launch {
            try {
                if (hasInternet) {

                    val result: NewsArticles = repository.getHeadlines()
                    newsArticles.postValue(result.articles)

                } else {
                    val result: List<ArticleCache> = repository.getCache()
                    Log.d(TAG, "${result}: ")
                    newsArticles.postValue(
                        result
                            ?.map { it.toArticle() }
                    )


                }
            } catch (e: Exception) {
                Log.d("TAG", "$e: ")
            }
            isInSearchMode.postValue(false)
        }


    }


    fun sort(query: String) {
        if (query == AppConstants.SORT_ITEMS[0]) {


            val articles = newsArticles.value?.sortedBy {
                it.publishedAt.substring(8, 10)
            }?.reversed()


            newsArticles.postValue(articles)

        } else {
            val articles = newsArticles.value?.sortedBy {
                it.title
            }

            newsArticles.postValue(articles)


        }
    }


    fun onQuery(query: String) {

        searchStringLiveData.postValue(query)

    }

    private fun resetSearchState() {
        newsArticles.value = listOf()
    }

    fun searchNews(query: String) {
        isInSearchMode.postValue(true)
        searchFailed.postValue(false)

        resetSearchState()
        viewModelScope.launch {
            try {

                val result = repository.searchArticle(query = query)

                if (result.totalResults == 0) {
                    searchFailed.postValue(true)
                } else
                    searchFailed.postValue(false)

                newsArticles.postValue(result.articles)
            } catch (e: Exception) {
                Log.d("TAG", "searchNews: $e")
            }
            isInSearchMode.postValue(false)
        }
    }


    fun insertArticle(articleLocal: ArticleLocal) = viewModelScope.launch {
        repository.insertArticle(articleLocal)
    }

    fun deleteArticle(articleLocal: String) = viewModelScope.launch {
        repository.deleteArticle(articleLocal)
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }

}