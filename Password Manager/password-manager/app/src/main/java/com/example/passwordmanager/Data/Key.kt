package com.example.passwordmanager.Data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "key_table")
data class Key(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val account: String,
    val username: String,
    val additional: String?,
    val password: String
): Parcelable