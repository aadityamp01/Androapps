package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var notesViewModel: NotesViewModel
    lateinit var adapter: NotesRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val notesDAO = NotesDatabase.getInstance(this).notesDAO
        val repository = NotesRepository(notesDAO)
        val factory = NotesViewModelFactory(repository)
        notesViewModel = ViewModelProvider(this,factory)[NotesViewModel :: class.java]
        binding.viewModel = notesViewModel
        binding.lifecycleOwner = this
        recyclerViewInit()
        notesViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun recyclerViewInit() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NotesRecyclerViewAdapter(this,
            {selectedItem : NotesEntity -> listItemClicked(selectedItem)})
        binding.recyclerView.adapter = adapter
        displayNotesList()

    }
    private fun displayNotesList() {
        notesViewModel.notes.observe(this, Observer {
            Log.i("MyTag",it.toString() )
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }
    private fun listItemClicked(notesEntity: NotesEntity) {
//        Toast.makeText(this,"Selected name is ${notesEntity.name}",Toast.LENGTH_SHORT).show()
        notesViewModel.initUpdateOrDelete(notesEntity)
    }
}