package com.shaun.newsbreeze.di

import com.shaun.newsbreeze.localdatabase.ArticleDao
import com.shaun.newsbreeze.network.NewsApiService
import com.shaun.newsbreeze.repository.HomeScreenRepository
import com.shaun.newsbreeze.repository.HomeScreenRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractiveModule {
    @Provides
    @Singleton

    fun provideRepository(apiKey: String, retrofit: NewsApiService,dao: ArticleDao,hasInternet:Boolean):HomeScreenRepository {
        return HomeScreenRepositoryImpl(apiKey = apiKey, retrofit = retrofit,dao,hasInternet)
    }
}