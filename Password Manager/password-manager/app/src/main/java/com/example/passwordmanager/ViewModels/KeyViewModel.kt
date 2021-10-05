package com.example.passwordmanager.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.Data.Key
import com.example.passwordmanager.Data.KeyDatabase
import com.example.passwordmanager.Data.KeyRepository
import kotlinx.coroutines.launch

class KeyViewModel(application: Application):AndroidViewModel(application) {
    private var readAllData = MutableLiveData<List<Key>>()
    private lateinit var repository:KeyRepository
    init {
        val keyDao = KeyDatabase.getDatabase(application).keyDao()
        repository = KeyRepository(keyDao)
    }

    private fun readKeyValues(){
        viewModelScope.launch {
            repository.readKeys()
            readAllData.value = repository.readAllKeys.value
        }
    }

    fun listKeys():LiveData<List<Key>>{
        return repository.readKeys()
    }

    fun addKey(key:Key){
        viewModelScope.launch {
            repository.addKey(key)
        }
    }

    fun updateKey(key:Key){
        viewModelScope.launch {
            repository.updateKey(key)
        }
    }

    fun deleteKey(key:Key){
        viewModelScope.launch {
            repository.deleteKey(key)
        }
    }
}