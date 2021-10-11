package com.akshatbhuhagal.volleyexemplar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // On Button Click
        btnCallApi.setOnClickListener {

            val queue = Volley.newRequestQueue(this)
            val url: String = "https://api.github.com/search/users?q=eyehunt"

            // Request a string response from the provided URL.
            val stringReq = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->

                    var strResp = response.toString()
                    val jsonObj: JSONObject = JSONObject(strResp)
                    val jsonArray: JSONArray = jsonObj.getJSONArray("items")
                    var str_user: String = ""
                    for (i in 0 until jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                        str_user = str_user + jsonInner.get("login") + "\n"
                    }
                    tvTitle!!.text = "Response : $str_user "
                },
                Response.ErrorListener { tvTitle!!.text = "That didn't work!" })
            queue.add(stringReq)


        }


    }

}