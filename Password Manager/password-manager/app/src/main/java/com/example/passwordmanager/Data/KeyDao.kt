package com.example.passwordmanager.Data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface KeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addKey(key: Key)

    @Query("SELECT * FROM `key_table` ORDER BY id")
    fun getAllKeys(): LiveData<List<Key>>

    @Update
    suspend fun updateKey(key:Key)

    @Delete
    suspend fun deleteKey(key: Key)
}