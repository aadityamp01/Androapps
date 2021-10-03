package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.notesapp.databinding.ActivitySlashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SlashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySlashScreenBinding
    private lateinit var fullscreenContent: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_slash_screen)
        supportActionBar!!.hide()
        CoroutineScope(Dispatchers.IO).launch {
                delay(2000)
                val i =Intent(this@SlashScreenActivity,MainActivity::class.java)
                startActivity(i)
                finish()
        }

    }
    }
