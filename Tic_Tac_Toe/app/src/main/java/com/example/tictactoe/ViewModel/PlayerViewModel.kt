package com.example.tictactoe.ViewModel

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class PlayerViewModel(application: Application) : AndroidViewModel(application) {
    var player1 = MutableLiveData<ArrayList<Int>>()
    var player2 = MutableLiveData<ArrayList<Int>>()
    var filled = MutableLiveData<ArrayList<Int>>()
    var result = MutableLiveData<Int>()
    var tempFilled = ArrayList<Int>()
    var tempPlayer2 = ArrayList<Int>()
    var tempPlayer1 = ArrayList<Int>()
    var playerTurn = MutableLiveData<Boolean>()
    var Turn = true
    fun player1Observer() : LiveData<ArrayList<Int>>{
        return player1
    }
    fun player2Observer() : LiveData<ArrayList<Int>>{
        return player2
    }
    fun resultObserver() : LiveData<Int>{
        return result
    }
    fun playerTurnObserver() : LiveData<Boolean>{
        return  playerTurn
    }
    fun move (pos : Int){
        if(Turn){
            if(!tempFilled.contains(pos)){
                tempPlayer1.add(pos)
                tempFilled.add(pos)
                Turn = false
                playerTurn.postValue(Turn)
                player1.postValue(tempPlayer1)
                filled.postValue(tempFilled)
                checkWinner()

            }
        }else{
            if(!tempFilled.contains(pos)){
                tempPlayer2.add(pos)
                tempFilled.add(pos)
                Turn = true
                playerTurn.postValue(Turn)
                player2.postValue(tempPlayer2)
                filled.postValue(tempFilled)
                checkWinner()

            }
        }

    }
    fun reset(){
        player1.value?.clear()
        player2.value?.clear()
        tempPlayer1.clear()
        tempPlayer2.clear()
        filled.value?.clear()
        tempFilled.clear()
        result.value=0
        result.postValue(0)
        Turn = true
        playerTurn.postValue(Turn)
    }
    fun checkWinner() : Boolean{
        var playerwins = false
        var computerwins = false
        var isDraw = false
        if(tempPlayer1.contains(1) && tempPlayer1.contains(2) && tempPlayer1.contains(3)){
            playerwins = true
        }else if(tempPlayer1.contains(4) && tempPlayer1.contains(5) && tempPlayer1.contains(6)){
            playerwins = true
        }else if(tempPlayer1.contains(7) && tempPlayer1.contains(8) && tempPlayer1.contains(9)){
            playerwins = true
        }else if(tempPlayer1.contains(1) && tempPlayer1.contains(4) && tempPlayer1.contains(7)){
            playerwins = true
        }else if(tempPlayer1.contains(2) && tempPlayer1.contains(5) && tempPlayer1.contains(8)){
            playerwins = true
        }else if(tempPlayer1.contains(3) && tempPlayer1.contains(6) && tempPlayer1.contains(9)){
            playerwins = true
        }else if(tempPlayer1.contains(1) && tempPlayer1.contains(5) && tempPlayer1.contains(9)){
            playerwins = true
        }else if(tempPlayer1.contains(3) && tempPlayer1.contains(5) && tempPlayer1.contains(7)){
            playerwins = true
        }

        if(tempPlayer2.contains(1) && tempPlayer2.contains(2) && tempPlayer2.contains(3)){
            computerwins = true
        }else if(tempPlayer2.contains(4) && tempPlayer2.contains(5) && tempPlayer2.contains(6)){
            computerwins = true
        }else if(tempPlayer2.contains(7) && tempPlayer2.contains(8) && tempPlayer2.contains(9)){
            computerwins = true
        }else if(tempPlayer2.contains(1) && tempPlayer2.contains(4) && tempPlayer2.contains(7)){
            computerwins = true
        }else if(tempPlayer2.contains(2) && tempPlayer2.contains(5) && tempPlayer2.contains(8)){
            computerwins = true
        }else if(tempPlayer2.contains(3) && tempPlayer2.contains(6) && tempPlayer2.contains(9)){
            computerwins = true
        }else if(tempPlayer2.contains(1) && tempPlayer2.contains(5) && tempPlayer2.contains(9)){
            computerwins = true
        }else if(tempPlayer2.contains(3) && tempPlayer2.contains(5) && tempPlayer2.contains(7)){
            computerwins = true
        }
        if(tempFilled.contains(1) &&
            tempFilled.contains(2) &&
            tempFilled.contains(3) &&
            tempFilled.contains(4) &&
            tempFilled.contains(5) &&
            tempFilled.contains(6) &&
            tempFilled.contains(7) &&
            tempFilled.contains(8) &&
            tempFilled.contains(9)
        ){
            isDraw = true
        }
        if(playerwins) {

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                reset()
            }, 1000)
            result.postValue(1)
            return true
        }else if(computerwins) {

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                reset()
            }, 1000)
            result.postValue(2)
            return true
        }else if(isDraw){

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                reset()
            }, 1000)
            result.postValue(3)
            return true
        }
        return false

    }

}