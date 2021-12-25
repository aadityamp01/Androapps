package com.example.NFTSalesViewer.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val NFTDao: NFTDao
) {

    fun readData(): Flow<List<NFT>> {
        return NFTDao.readData()
    }

//    suspend fun insertData(nft_sales: NFT) {
//        nft_sales.insertData(nft_sales)
//    }

    fun searchDatabase(searchQuery: String): Flow<List<NFT>> {
        return NFTDao.searchDatabase(searchQuery)
    }

}