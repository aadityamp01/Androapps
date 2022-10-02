package com.example.biometricauth

import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.ShareCompat.IntentReader.from

class MainActivity : AppCompatActivity() {
    private var CancellationSignal: CancellationSignal? = null
    private val AuthenticationCallback: BiometricPrompt.AuthenticationCallback
        get() =
            @RequiresApi(Build.VERSION_CODES.P)
    object : BiometricPrompt.AuthenticationCallback(){
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
            super.onAuthenticationError(errorCode, errString)
            toastMessage("Authentication ERROR: $errString")
        }
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
            super.onAuthenticationSucceeded(result)
            toastMessage("Authentication Verified")
            intent()
        }
    }
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val auth = findViewById<Button>(R.id.btn_auth)
       checkBiometricSupport()
    auth.setOnClickListener {
        val biometricPrompt = BiometricPrompt.Builder(this)
            .setTitle("Biometric Login for the app")
            .setSubtitle("Very User using Biometric Credentials")
            .setNegativeButton("Cancel", this.mainExecutor, DialogInterface.OnClickListener
            { dialogInterface, unit ->
                toastMessage("Authentication Cancelled")
            }).build()
        biometricPrompt.authenticate(GetCancellationSignal(), mainExecutor, AuthenticationCallback)
    }
    }

    private fun checkBiometricSupport() : Boolean {
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!keyguardManager.isKeyguardSecure){
            toastMessage("Fingerprint Auth not Enabled")
            return false
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.USE_BIOMETRIC)
            != PackageManager.PERMISSION_GRANTED){
            toastMessage("Fingerprint Auth not Enabled")
            return false
        }
        return true
    }

    private fun toastMessage(message :  String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
    private fun intent(){
        startActivity(Intent(this, Verified::class.java))
    }
    private fun GetCancellationSignal(): CancellationSignal{
        CancellationSignal = CancellationSignal()
        CancellationSignal?.setOnCancelListener {
            toastMessage("Auh Canceled")
        }
        return CancellationSignal as CancellationSignal
    }
}