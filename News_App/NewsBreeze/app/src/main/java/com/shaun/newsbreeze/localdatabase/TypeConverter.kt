package com.shaun.newsbreeze.localdatabase

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shaun.newsbreeze.models.Article
import com.shaun.newsbreeze.models.Source

class TypeConverter {
    private val gSon = Gson()

    @TypeConverter
    fun fromSource(source: Source): String {
        return gSon.toJson(source)
    }

    @TypeConverter
    fun tvSource(name: String): Source {
        val listType = object : TypeToken<Source>() {}.type
        return gSon.fromJson(name, listType)
    }


}

fun Article?.toArticleLocal(): ArticleLocal {
    return ArticleLocal(
        source = this?.source,
        author = this?.author,
        title = this?.title,
        description = this?.description,
        url = this?.url,
        urlToImage = this?.urlToImage,
        publishedAt = this?.publishedAt,
        content = this?.content,

        )
}

fun Article?.toArticleCache(): ArticleCache {
    return ArticleCache(
        source = this?.source,
        author = this?.author,
        title = this?.title,
        description = this?.description,
        url = this?.url,
        urlToImage = this?.urlToImage,
        publishedAt = this?.publishedAt,
        content = this?.content,

        )
}