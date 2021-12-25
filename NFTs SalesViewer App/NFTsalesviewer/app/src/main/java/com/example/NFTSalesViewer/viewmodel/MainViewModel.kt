package com.example.NFTSalesViewer.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.NFTSalesViewer.data.NFT
import com.example.NFTSalesViewer.data.Repository

class MainViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val readData = repository.readData().asLiveData()

//    fun insertData(nft_sales: NFT){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.insertData(nft_sales)
//        }
//    }

    fun searchDatabase(searchQuery: String): LiveData<List<NFT>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

}