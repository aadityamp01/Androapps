package com.shaun.newsbreeze.localdatabase

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.shaun.newsbreeze.models.Article
import com.shaun.newsbreeze.models.Source


@Entity(tableName = "article", indices = [Index(value = ["title"], unique = true)])
data class ArticleLocal(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null

)

@Entity(tableName = "article_cache", indices = [Index(value = ["title"], unique = true)])
data class ArticleCache(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null

)


fun ArticleLocal.toArticle(): Article {
    return Article(
        source ?: Source("0", "null"),
        author.toString(),
        title.toString(),
        description.toString(),
        url.toString(),
        urlToImage.toString(),
        publishedAt.toString(),
        content.toString()
    )
}

fun ArticleCache.toArticle(): Article {
    return Article(
        source ?: Source("0", "null"),
        author.toString(),
        title.toString(),
        description.toString(),
        url.toString(),
        urlToImage.toString(),
        publishedAt.toString(),
        content.toString()
    )
}