package com.akshatbhuhagal.recyclerviewexemplar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshatbhuhagal.recyclerviewexemplar.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity(), MyAdapter.ItemClicked {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val items = fetchData()
        val adapter = MyAdapter(items, this)

        binding.recyclerView.adapter = adapter

    }

    private fun fetchData() : ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0 until 100) {
            list.add("Item $i")
        }
        return list
    }

    override fun onItemClicked(item: String) {
        Toast.makeText(this, "Clicked on $item", Toast.LENGTH_SHORT).show()
    }

}