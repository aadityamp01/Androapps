package com.example.NFTSalesViewer.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NFT::class],
    version = 1,
    exportSchema = true
)
abstract class NFTSalesDatabase: RoomDatabase() {

    abstract fun nftDao(): NFTDao

}