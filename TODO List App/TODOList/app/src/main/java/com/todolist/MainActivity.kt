package com.todolist

import android.app.AlertDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.todolist.databinding.ActivityMainBinding
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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

        setupTaskIntoRecyclerView()
    }

    private fun setupTaskIntoRecyclerView(){
        if(getItemList().size > 0){

            binding.rvTaskList.visibility = View.VISIBLE
            binding.tvNoTaskAvailable.visibility = View.GONE

            // Set the LayoutManager that this RecyclerView will use.
            binding.rvTaskList.layoutManager = LinearLayoutManager(this)

            val itemAdapter = ItemAdapter(this, getItemList())
            binding.rvTaskList.adapter = itemAdapter
        }else{
            binding.rvTaskList.visibility = View.GONE
            binding.tvNoTaskAvailable.visibility = View.VISIBLE
        }
    }

    // Function is used to get list of items added in the database
    private fun getItemList(): ArrayList<TDataModel> {
        val databaseHandler = DatabaseHandler(this)

        return databaseHandler.viewTask()
    }


    private fun addTask() {
        val task = binding.etTasks.text.toString()
        val description = binding.etDes.text.toString()
        val databaseHandler = DatabaseHandler(this)

        if(task.isNotEmpty() && description.isNotEmpty()){
            val status = databaseHandler.addTasks(TDataModel(0,task,description))

            if(status > -1){
                Toast.makeText(applicationContext, "Task Added Successfully", Toast.LENGTH_SHORT).show()
                // Clearing the text views automatically after tasks saved
                binding.etTasks.text?.clear()
                binding.etDes.text?.clear()

                setupTaskIntoRecyclerView()
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

        updateDialog.findViewById<EditText>(R.id.etUpdateTask).setText(TDataModel.Task)
        updateDialog.findViewById<EditText>(R.id.etUpdateDes).setText(TDataModel.Description)

        updateDialog.findViewById<TextView>(R.id.tvUpdate).setOnClickListener{

            val task = updateDialog.findViewById<EditText>(R.id.etUpdateTask).text.toString()
            val description = updateDialog.findViewById<EditText>(R.id.etUpdateDes).text.toString()

            val databaseHandler = DatabaseHandler(this)

            if(task.isNotEmpty() && description.isNotEmpty()){
                val status = databaseHandler.updateTask(TDataModel(TDataModel.id,task,description))

                if(status > -1){
                    Toast.makeText(applicationContext, "Task Updated Successfully", Toast.LENGTH_SHORT).show()

                    setupTaskIntoRecyclerView()
                    updateDialog.dismiss()
                }
            }else{
                Toast.makeText(applicationContext, "Empty fields can't be updated", Toast.LENGTH_SHORT).show()
            }
        }

        updateDialog.findViewById<TextView>(R.id.tvCancel).setOnClickListener{
            updateDialog.dismiss()
        }

        updateDialog.show()

    }

    fun deleteRecordDialog(TDataModel: TDataModel){
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Delete Task")

        builder.setMessage("Are you sure you wants to delete ${TDataModel.id}")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Yes"){ dialogInterface, _ ->
            val databaseHandler = DatabaseHandler(this)
            val status = databaseHandler.deleteTask(TDataModel(TDataModel.id,"",""))

            if(status > -1){
                Toast.makeText(applicationContext, "Task Deleted Successfully", Toast.LENGTH_SHORT).show()

                setupTaskIntoRecyclerView()
            }

            dialogInterface.dismiss()
        }

        builder.setNegativeButton("No") { dialogInterface, _->
            dialogInterface.dismiss()
        }

        val alertDialog: AlertDialog = builder.create()

        // Set other dialog properties

        alertDialog.setCancelable(false)  // Will not allow user to cancel the dialog
        alertDialog.show()
    }
}
