package com.akshatbhuhagal.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.akshatbhuhagal.viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        increaseText()

        binding.btnIncrease.setOnClickListener {
            increaseButton()
        }

    }

    fun increaseText() {
        binding.tvNumber.text = mainViewModel.count.toString()
    }

    fun increaseButton() {
        mainViewModel.onIncrease()
        increaseText()
    }

}