package com.example.tictactoe.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tictactoe.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class StatsViewModel(application: Application): AndroidViewModel(application) {

    private var db = Firebase.firestore.collection("Users")
    private var auth = FirebaseAuth.getInstance()
    var User = MutableLiveData<UserModel?>()
    fun UserObserve() : LiveData<UserModel?>{
        return User
    }
    fun getUser() {
        try {
            var query = db
                .whereEqualTo("uid" , auth.currentUser?.uid)
                .get()
                .addOnSuccessListener {
                    val list : List<DocumentSnapshot> = it.documents
                    for(currUser in list){
                        val user : UserModel? = currUser.toObject(UserModel::class.java)
                        if(User != null && user?.uid == auth.currentUser?.uid){
                            User.postValue(user)
                            Log.d("@@AtStatsviewModel" , user.toString())

                            break
                        }
                    }
                }

        }catch(e : Exception){
            Log.d("@@AtStatsviewModel" , e.message!!)
        }
    }
}