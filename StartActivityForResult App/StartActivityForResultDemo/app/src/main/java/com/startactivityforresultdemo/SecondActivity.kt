package com.startactivityforresultdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

// Add a Second Activity
// START
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Add a click event for submit button which will set the result and finish the activity.
        btn_submit.setOnClickListener {

            // This is used send the custom data with the result.
            val intent = Intent()
            intent.putExtra(MainActivity.NAME, et_name.text.toString())
            intent.putExtra(MainActivity.EMAIL, et_email.text.toString())

            setResult(Activity.RESULT_OK, intent) // It is used to set the RESULT OK and a custom data values which we wants to send back.
            finish() // FINISH the activity
        }
        // END
    }
}
// END