package com.example.wordshuffle

import android.os.Bundle
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String

class HintFragmentDirections private constructor() {
  private data class ActionHintFragmentToGameFragment(
    val username: String = "RandomUser",
    val password: String = "int_password"
  ) : NavDirections {
    override fun getActionId(): Int = R.id.action_hintFragment_to_gameFragment

    override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("username", this.username)
      result.putString("password", this.password)
      return result
    }
  }

  companion object {
    fun actionHintFragmentToGameFragment(username: String = "RandomUser", password: String =
        "int_password"): NavDirections = ActionHintFragmentToGameFragment(username, password)
  }
}
