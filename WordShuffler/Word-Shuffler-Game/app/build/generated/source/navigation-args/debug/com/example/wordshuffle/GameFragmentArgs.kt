package com.example.wordshuffle

import android.os.Bundle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

data class GameFragmentArgs(
  val username: String = "RandomUser",
  val password: String = "int_password"
) : NavArgs {
  fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("username", this.username)
    result.putString("password", this.password)
    return result
  }

  companion object {
    @JvmStatic
    fun fromBundle(bundle: Bundle): GameFragmentArgs {
      bundle.setClassLoader(GameFragmentArgs::class.java.classLoader)
      val __username : String?
      if (bundle.containsKey("username")) {
        __username = bundle.getString("username")
        if (__username == null) {
          throw IllegalArgumentException("Argument \"username\" is marked as non-null but was passed a null value.")
        }
      } else {
        __username = "RandomUser"
      }
      val __password : String?
      if (bundle.containsKey("password")) {
        __password = bundle.getString("password")
        if (__password == null) {
          throw IllegalArgumentException("Argument \"password\" is marked as non-null but was passed a null value.")
        }
      } else {
        __password = "int_password"
      }
      return GameFragmentArgs(__username, __password)
    }
  }
}
