package com.shaun.newsbreeze.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ArticleLocal::class, ArticleCache::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class NewsBreezeDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
