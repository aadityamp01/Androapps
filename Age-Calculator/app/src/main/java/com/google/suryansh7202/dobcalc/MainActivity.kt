package com.google.suryansh7202.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvAgeinHours : TextView? = null
    private var tvAgeinMinutes : TextView? = null
    private var tvAgeinSeconds : TextView? = null
    private var tvAgeinDays : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeinHours = findViewById(R.id.tvAgeInHours)
        tvAgeinMinutes =findViewById(R.id.tvAgeInMinutes)
        tvAgeinSeconds = findViewById(R.id.tvAgeInSeconds)
        tvAgeinDays = findViewById(R.id.tvAgeInDays)

        // current date show on selected date
        val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
        currentDate?.let {
            tvSelectedDate?.text = currentDate.toString()
        }
        btnDatePicker.setOnClickListener {
            clickDatePicker()

        }

    }

    private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month =  myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
       {_,selectedyear,selectedmonth,selecteddayOfMonth ->

           val selectedDate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"
           tvSelectedDate?.text = selectedDate


           val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
           val theDate = sdf.parse(selectedDate)
           theDate?.let {
               val selectedDateInDays = theDate.time/ 86400000
               val selectedDateInMinutes = theDate.time/ 60000
               val selectedDateInHours = theDate.time/ 3600000
               val selectedDateInSeconds = theDate.time/ 1000



               val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
               currentDate?.let {
                   val currentDateInDays = currentDate.time/ 86400000
                   val currentDateInMinutes = currentDate.time/ 60000
                   val currentDateInHours = currentDate.time/ 3600000
                   val currentDateInSeconds = currentDate.time/ 1000

                   val differenceInDays = currentDateInDays-selectedDateInDays
                   val differenceInMinutes = currentDateInMinutes-selectedDateInMinutes
                   val differenceInHours = currentDateInHours-selectedDateInHours
                   val differenceInSeconds = currentDateInSeconds-selectedDateInSeconds


                   tvAgeinDays?.text = differenceInDays.toString()
                   tvAgeinMinutes?.text = differenceInMinutes.toString()
                   tvAgeinHours?.text = differenceInHours.toString()
                   tvAgeinSeconds?.text = differenceInSeconds.toString()


               }

           }





       },
       year,
       month,
       day
   )

        dpd.datePicker.maxDate = System.currentTimeMillis()- 86400000

        dpd.show()



    }


}