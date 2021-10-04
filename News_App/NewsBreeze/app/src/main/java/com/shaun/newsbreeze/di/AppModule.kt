package com.shaun.newsbreeze.di

import android.content.Context
import androidx.room.Room
import com.shaun.newsbreeze.NewsBreezeApplication
import com.shaun.newsbreeze.localdatabase.ArticleDao
import com.shaun.newsbreeze.localdatabase.NewsBreezeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApplication(@ApplicationContext app: Context): NewsBreezeApplication {
        return app as NewsBreezeApplication
    }

    @Singleton
    @Provides
    fun injectDao(
        database: NewsBreezeDatabase
    ): ArticleDao = database.articleDao()

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context,NewsBreezeDatabase::class.java,"offlineArticles").build()

}
