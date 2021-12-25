package com.example.NFTSalesViewer.di

import android.content.Context
import androidx.room.Room
import com.example.NFTSalesViewer.data.NFTSalesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NFTSalesDatabase::class.java,
        "nftsalesdb_database"
    ).createFromAsset("Databases/nft_sales.db")
        .build()

    @Singleton
    @Provides
    fun provideDao(database: NFTSalesDatabase) = database.nftDao()

}