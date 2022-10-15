package com.example.tictactoe.ViewModel

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tictactoe.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.xml.transform.dom.DOMLocator

class ComputerViewModel(application: Application): AndroidViewModel(application) {
    var player = MutableLiveData<ArrayList<Int>>()
    var computer = MutableLiveData<ArrayList<Int>>()
    var filled = MutableLiveData<ArrayList<Int>>()
    var result = MutableLiveData<Int>()
    var tempFilled = ArrayList<Int>()
    var tempComputer = ArrayList<Int>()
    var tempPlayer = ArrayList<Int>()
    var playerTurn = MutableLiveData<Boolean>()
    var winPercentage = MutableLiveData<Double>()
    var db = Firebase.firestore.collection("Users")
    var auth = FirebaseAuth.getInstance()
    fun playerTurnObserve() : LiveData<Boolean>{
        return playerTurn
    }
    fun playerObserve() : LiveData<ArrayList<Int>>{
        return player
    }
    fun resultObserve() : LiveData<Int>{
        return result
    }
    fun computerObserve() : LiveData<ArrayList<Int>>{
        return computer
    }
    fun winPercentageObserve() : LiveData<Double>{
        return winPercentage
    }
    fun playerTurn(pos : Int) {
        if(!tempFilled.contains(pos)!!){
            tempFilled.add(pos)
            tempPlayer.add(pos)
            Log.d("@@FromViewModelFilled", tempFilled.toString())
            playerTurn.postValue(false)
            player.postValue(tempPlayer)
            filled.postValue(tempFilled)
            if(!checkWinner()){
                    Handler(Looper.getMainLooper()).postDelayed(Runnable {
                       computerTurn(getrandom())
                    }, 1000)
                }
        }
    }
   fun addUser(user : UserModel) = CoroutineScope(Dispatchers.IO).launch {
       try {
           val query = db
               .whereEqualTo("uid",user.uid)
               .get()
               .addOnSuccessListener {
                   val list : List<DocumentSnapshot> = it.documents
                   for(currUser in list){
                       val User : UserModel? = currUser.toObject(UserModel::class.java)
                       if(User != null && User.uid == auth.currentUser?.uid){
                           var userold = User
                           userold.noOWins = userold.noOWins?.plus(user.noOWins!!)
                           userold.noOLoss = userold.noOLoss?.plus(user.noOLoss!!)
                           userold.totalMatches = userold.totalMatches?.plus(user.totalMatches!!)
                           Log.d("@@FromAddUser","${userold}")
                           for(value in it){
                               db.document(value.id).set(userold, SetOptions.merge()).addOnSuccessListener {
                                   Log.d("@@FromAddUser","Success")
                               }.addOnFailureListener {
                                   Log.d("@@FromAddUser",it.message!!)
                               }
                           }
                           break
                       }
                   }
               }
            if(query.await().documents.isEmpty()){
                db.add(user)
            }

       }catch (e :Exception){
           Log.d("@@FromComputerViewModel" , e.message!!)
       }
   }
    fun computerTurn(rand : Int) {
        if(!tempFilled.contains(rand)){
           tempFilled.add(rand)
            tempComputer.add(rand)
            playerTurn.postValue(true)
            Log.d("@@FromViewModelFilled", tempFilled.toString())
            computer.postValue(tempComputer)
            filled.postValue(tempFilled)
            checkWinner()
        }
    }
    fun getrandom() : Int{
        var ran = Random()
        var rand = ran.nextInt(9)
        rand++
            if(tempFilled.contains(rand)){
                return getrandom()
            }else{
                return rand
            }

        Log.d("@@Random", rand.toString())
       return rand
    }
    fun reset() {
        player.value?.clear()
        computer.value?.clear()
        filled.value?.clear()
        tempPlayer.clear()
        tempFilled.clear()
        tempComputer.clear()
        result.postValue(0)
        playerTurn.postValue(true)
    }
    fun checkWinner() : Boolean{
        var playerwins = false
        var computerwins = false
        var isDraw = false
        if(tempPlayer.contains(1) && tempPlayer.contains(2) && tempPlayer.contains(3)){
            playerwins = true
        }else if(tempPlayer.contains(4) && tempPlayer.contains(5) && tempPlayer.contains(6)){
            playerwins = true
        }else if(tempPlayer.contains(7) && tempPlayer.contains(8) && tempPlayer.contains(9)){
            playerwins = true
        }else if(tempPlayer.contains(1) && tempPlayer.contains(4) && tempPlayer.contains(7)){
            playerwins = true
        }else if(tempPlayer.contains(2) && tempPlayer.contains(5) && tempPlayer.contains(8)){
            playerwins = true
        }else if(tempPlayer.contains(3) && tempPlayer.contains(6) && tempPlayer.contains(9)){
            playerwins = true
        }else if(tempPlayer.contains(1) && tempPlayer.contains(5) && tempPlayer.contains(9)){
            playerwins = true
        }else if(tempPlayer.contains(3) && tempPlayer.contains(5) && tempPlayer.contains(7)){
            playerwins = true
        }

        if(tempComputer.contains(1) && tempComputer.contains(2) && tempComputer.contains(3)){
            computerwins = true
        }else if(tempComputer.contains(4) && tempComputer.contains(5) && tempComputer.contains(6)){
            computerwins = true
        }else if(tempComputer.contains(7) && tempComputer.contains(8) && tempComputer.contains(9)){
            computerwins = true
        }else if(tempComputer.contains(1) && tempComputer.contains(4) && tempComputer.contains(7)){
            computerwins = true
        }else if(tempComputer.contains(2) && tempComputer.contains(5) && tempComputer.contains(8)){
            computerwins = true
        }else if(tempComputer.contains(3) && tempComputer.contains(6) && tempComputer.contains(9)){
            computerwins = true
        }else if(tempComputer.contains(1) && tempComputer.contains(5) && tempComputer.contains(9)){
            computerwins = true
        }else if(tempComputer.contains(3) && tempComputer.contains(5) && tempComputer.contains(7)){
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