package com.akshatbhuhagal.greetingcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akshatbhuhagal.greetingcard.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val userName = intent.getStringExtra(EXTRA_MESSAGE)
        binding.tvName.text = "Hello ${userName!!.substring(0,1).toUpperCase() + userName!!.substring(1).toLowerCase()}"

        binding.btnBack.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }



    }
}