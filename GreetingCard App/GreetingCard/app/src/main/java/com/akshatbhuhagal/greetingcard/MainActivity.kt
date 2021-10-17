package com.akshatbhuhagal.greetingcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.akshatbhuhagal.greetingcard.databinding.ActivityMainBinding

const val EXTRA_MESSAGE = "com.akshatbhuhagal.greetingcard.MESSAGE"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()



            binding.btnWelcome.setOnClickListener {

                val userName = binding.etName.text.toString()

                val intent = Intent(this, WelcomeActivity::class.java).apply {

                    putExtra(EXTRA_MESSAGE, userName)

                }

                if (binding.etName.text.isNullOrEmpty()) {
                    Toast.makeText(this, "Enter your Name", Toast.LENGTH_SHORT).show()
                } else {
                    startActivity(intent)
                }


            }


    }
}