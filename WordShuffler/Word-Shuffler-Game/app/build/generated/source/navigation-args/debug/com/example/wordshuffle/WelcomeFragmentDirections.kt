package com.example.wordshuffle

import android.os.Bundle
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String

class WelcomeFragmentDirections private constructor() {
  private data class ActionWelcomeFragmentToGameFragment(
    val username: String = "RandomUser",
    val password: String = "int_password"
  ) : NavDirections {
    override fun getActionId(): Int = R.id.action_welcomeFragment_to_gameFragment

    override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("username", this.username)
      result.putString("password", this.password)
      return result
    }
  }

  companion object {
    fun actionWelcomeFragmentToGameFragment(username: String = "RandomUser", password: String =
        "int_password"): NavDirections = ActionWelcomeFragmentToGameFragment(username, password)
  }
}
