package com.example.texttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import com.example.texttospeech.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener{

    private var ttospeech: TextToSpeech? = null  //creating var for text to speech

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ttospeech = TextToSpeech(this,this)

        binding.btSpeak.setOnClickListener {
            if(binding.etTts.text.isEmpty()){
                Toast.makeText(this, "Enter text to speak", Toast.LENGTH_SHORT).show()
            }else{
                speakOut(binding.etTts.text.toString())
            }
        }

    }

    override fun onInit(status: Int) {

        if(status == TextToSpeech.SUCCESS){
            val result = ttospeech!!.setLanguage(Locale.US)

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The language is not supported" )
            }
        }
        else{
            Log.e("TTA", "Initialization failed!")
        }
    }


    private fun speakOut(text: String) {
        ttospeech!!.speak(text, TextToSpeech.QUEUE_ADD, null,"")  // Check speak function in detail if you require utteranceId
    }


}