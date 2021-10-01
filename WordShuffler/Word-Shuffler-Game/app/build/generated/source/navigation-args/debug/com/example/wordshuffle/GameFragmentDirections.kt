package com.example.wordshuffle

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

class GameFragmentDirections private constructor() {
  companion object {
    fun actionGameFragmentToHintFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_gameFragment_to_hintFragment)
  }
}
