package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.ListItemBinding

class NotesRecyclerViewAdapter(private val context: Context,
                               private val listItemClicked : (NotesEntity) -> Unit) : RecyclerView.Adapter<MyViewHolder>() {

    private val notesList = ArrayList<NotesEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding : ListItemBinding = DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        return  MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(notesList[position],listItemClicked)
    }


    override fun getItemCount(): Int {
        return notesList.size
    }
    fun setList(notes: List<NotesEntity>) {
        notesList.clear()
        notesList.addAll(notes)
    }

}
class MyViewHolder(private val binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

     fun bind(notes: NotesEntity, listItemClicked : (NotesEntity) -> Unit) {
        binding.nameTextView.text = notes.noteTitle
        binding.emailTextView.text = notes.noteSub
         binding.cardView.setOnClickListener({
             listItemClicked(notes)
         })
    }
}