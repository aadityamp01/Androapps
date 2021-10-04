package com.shaun.newsbreeze.network

import com.shaun.newsbreeze.models.NewsArticles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {


    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") countryCode: String = "in",
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int = 50
    ): NewsArticles


    @GET("everything")
    suspend fun searchArticles(
        @Query("q") query: String,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String
    ): NewsArticles
}


