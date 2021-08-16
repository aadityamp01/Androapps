package com.todolist

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.todolist.databinding.ActivityMainBinding
import com.todolist.databinding.DialogUpdateBinding
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogUpdateBinding: DialogUpdateBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarTodoActivity)
        binding.toolbarTodoActivity.title = R.string.app_name.toString()

        binding.btAddTasks.setOnClickListener {
            addTask()
        }
    }

    private fun setupTaskIntoRecyclerView(){
        if(getItemList().size > 0){

            binding.rvTaskList.visibility = View.VISIBLE
            binding.tvNoTaskAvailable.visibility = View.GONE

            val itemAdapter = ItemAdapter(this, getItemList())
            binding.rvTaskList.adapter = itemAdapter
        }


    }

    private fun getItemList(): ArrayList<TDataModel> {
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

        return databaseHandler.viewTask()
    }


    private fun addTask() {
        val task = binding.etTasks.text.toString()
        val description = binding.etDes.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

        if(task.isNotEmpty() && description.isNotEmpty()){
            val status = databaseHandler.addTasks(TDataModel(0,task,description))

            if(status > -1){
                Toast.makeText(applicationContext, "Task Added Successfully", Toast.LENGTH_SHORT).show()
                // Clearing the text views automatically after tasks saved
                binding.etTasks.text?.clear()
                binding.etDes.text?.clear()
            }
        }
        else{
            Toast.makeText(applicationContext, "Add Task", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateRecordDialog(TDataModel: TDataModel){
        val updateDialog = Dialog(this,R.style.Theme_AppCompat_Dialog)
        updateDialog.setCancelable(false)

        updateDialog.setContentView(R.layout.dialog_update)

    }

    fun deleteRecordDialog(TDataModel: TDataModel){
        val deleteDialog = Dialog(this,R.style.Theme_AppCompat_Dialog)
        deleteDialog.setCancelable(true)
    }
}
