package com.example.wordshuffle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameFragmentViewModel : ViewModel() {
    val name: String = "randomname"
    var score: Int = 0
    private val livescore= MutableLiveData<Int>()
    fun score_return():LiveData<Int>{
        return livescore
    }
    var randomword: String = "start"
    var scrambled: String = "sartt"
    fun addnum(){
        score += 10
        livescore.value = score
    }
    fun subnum_five(){
        score -= 5
        livescore.value = score
    }
    fun subnum_one(){
        score-=1
        livescore.value = score
    }
}