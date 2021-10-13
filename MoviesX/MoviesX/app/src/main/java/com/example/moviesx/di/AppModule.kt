package com.example.moviesx.di

import com.example.moviesx.data.remote.ApiService
import com.example.moviesx.data.remote.dataSource.MovieDataSource
import com.example.moviesx.data.remote.dataSource.MovieDataSourceImpl
import com.example.moviesx.data.repository.MovieRepository
import com.example.moviesx.data.repository.MovieRepositoryImpl
import com.example.moviesx.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun providesMovieDataSource(apiService: ApiService): MovieDataSource =
        MovieDataSourceImpl(apiService)

    @Provides
    fun providesMovieRepository(movieDataSource: MovieDataSource): MovieRepository =
        MovieRepositoryImpl(movieDataSource)
}
