package com.example.biometric

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialise biometric
        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this@MainActivity, executor, object :BiometricPrompt.AuthenticationCallback(){

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                //stop the tasks that require authentication

                authStatus.text = "Authentication Error: $errString"
                Toast.makeText(this@MainActivity,"Authentication Error: $errString", Toast.LENGTH_SHORT).show()

            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                //successful authentication

                authStatus.text = "Successful"
                Toast.makeText(this@MainActivity,"Authentication Successful", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                // failed authentication

                authStatus.text="Failed"
                Toast.makeText(this@MainActivity,"Authentication Failed", Toast.LENGTH_SHORT).show()
            }
        })

        //setting basic properties on dialog
        promptInfo = BiometricPrompt.PromptInfo.Builder().setTitle("Biometric Authentication")
            .setSubtitle("Login using Fingerprint Authentication")
            .setNegativeButtonText("Use App paasword instead")
            .build()


        //handling click and start authentication dialog
        authBton.setOnClickListener {

            biometricPrompt.authenticate(promptInfo)
        }
    }
}