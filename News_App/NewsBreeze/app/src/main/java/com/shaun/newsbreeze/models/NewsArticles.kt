package com.shaun.newsbreeze.models

data class NewsArticles(

    var status: String = "ok",
    var totalResults: Int = 1,
    var articles: List<Article> = arrayListOf()

)


