package com.example.myfavmemes

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shareBtn : Button = findViewById<Button>(R.id.shareBtn)
        val nextBtn : Button = findViewById<Button>(R.id.nextBtn)
        loadmeme()
        nextBtn.setOnClickListener {
            loadmeme()
        }
        shareBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"Checkout this awesome meme from reddit : $currentImgUrl" )
            val chooser =Intent.createChooser(intent , "Select an app :")
            startActivity(chooser)
        }

    }
    var currentImgUrl :String? = null

    //loadmeme function defined here
    private fun loadmeme(){
        val subredditTxt : TextView = findViewById(R.id.subredditTxt)
        val progressBar : ProgressBar = findViewById(R.id.progressBar)
        progressBar.visibility= View.VISIBLE
        // Instantiate the RequestQueue.

        val queue = Volley.newRequestQueue(this)
        val api_link = "https://meme-api.herokuapp.com/gimme"

        // Request a jsonObject response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            api_link,
            null,
            { response ->
                currentImgUrl = response.getString("url")
                val subreddit =response.getString("subreddit")
                Glide.with(this).load(currentImgUrl).listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }
                }).into(findViewById(R.id.memeImg))
                subredditTxt.text = subreddit
            },
            {
                Toast.makeText(this , "oops try again :(" , Toast.LENGTH_SHORT ).show()
            }
        )

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }
}