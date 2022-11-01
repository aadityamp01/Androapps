package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth

class splashActivity: AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()
        val User = auth.currentUser

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            if(User != null){
                Log.d("@@Start", User.email.toString())
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this,SignInActivity::class.java))
                finish()
            }
        }, 1000)
    }
}