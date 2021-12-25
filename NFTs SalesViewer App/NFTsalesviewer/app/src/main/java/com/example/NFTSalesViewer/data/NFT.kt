package com.example.NFTSalesViewer.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nft_sales")
data class NFT(
    @PrimaryKey val salesId: Int,
    @NonNull
    val Collections: String,
    @NonNull
    val Sales: String,
    @NonNull
    val Buyers: String,
    @NonNull
    val Txns: String,
    @NonNull
    val Owners: String,
)
