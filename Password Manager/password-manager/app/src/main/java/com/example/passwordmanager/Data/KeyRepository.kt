package com.example.passwordmanager.Data

import androidx.lifecycle.LiveData

class KeyRepository(private val keyDao: KeyDao) {
    lateinit var readAllKeys:LiveData<List<Key>>

    fun readKeys():LiveData<List<Key>>{
        return keyDao.getAllKeys()
    }

    suspend fun addKey(key:Key){
        keyDao.addKey(key)
    }

    suspend fun updateKey(key:Key){
        keyDao.updateKey(key)
    }

    suspend fun deleteKey(key:Key){
        keyDao.deleteKey(key)
    }
}