package com.example.NFTSalesViewer.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NFTDao {

    @Query("SELECT * FROM nft_sales ORDER BY salesId ASC")
    fun readData(): Flow<List<NFT>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertData(nft_sales: NFT)

    @Query("SELECT * FROM nft_sales WHERE salesId LIKE :searchQuery or Collections LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<NFT>>

}