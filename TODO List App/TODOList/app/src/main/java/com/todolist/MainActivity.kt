package com.todolist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.todolist.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarTodoActivity)
        binding.toolbarTodoActivity.title = R.string.app_name.toString()

        getAllCompletedDates()
    }


    private fun getAllCompletedDates() {

        // Instance of the Sqlite Open Helper class.
        val dbHandler = SqliteOpenHelper(this, null)

        val allCompletedDatesList =
            dbHandler.getAllCompletedDatesList() // List of history table data

        //  We will pass that list to the adapter class which we have created and bind it to the recycler view.)
        if (allCompletedDatesList.size > 0) {
            // Here if the List size is greater then 0 we will display the item in the recycler view or else we will show the text view that no data is available.
            binding.rvTaskList.visibility = View.VISIBLE
            binding.rvTaskList.visibility = View.VISIBLE
            binding.tvNoTaskAvailable.visibility = View.GONE

            // Creates a vertical Layout Manager
            binding.rvTaskList.layoutManager = LinearLayoutManager(this)

            // History adapter is initialized and the list is passed in the param.
            val itemAdapter = ItemAdapter(this, allCompletedDatesList)

            // Access the RecyclerView Adapter and load the data into it
            binding.rvTaskList.adapter = itemAdapter
        } else {
            binding.rvTaskList.visibility = View.GONE
            binding.rvTaskList.visibility = View.GONE
            binding.tvNoTaskAvailable.visibility = View.VISIBLE
        }
    }
}