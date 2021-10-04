package com.shaun.newsbreeze.localdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(articleLocal: ArticleLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticleCache(articleCache: ArticleCache)


    @Query("DELETE FROM article where title=:title")
    suspend fun deleteArticle(title: String)

    @Query("SELECT * FROM article")
    fun observeArticles(): LiveData<List<ArticleLocal>>

    @Query("SELECT * FROM article_cache")
    suspend fun observeArticleCache(): List<ArticleCache>


}