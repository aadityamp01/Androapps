package com.example.wordshuffle

import android.os.Bundle
import android.text.InputType
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.wordshuffle.databinding.ActivityMainBinding.inflate
import com.example.wordshuffle.databinding.FragmentGameBinding
import com.example.wordshuffle.databinding.FragmentGameBinding.inflate
import com.example.wordshuffle.databinding.FragmentHintBinding.inflate
import com.example.wordshuffle.databinding.FragmentWelcomeBinding.inflate
import com.google.android.material.snackbar.Snackbar

class GameFragment : Fragment() {
    val args: GameFragmentArgs by navArgs()
    var _binding: FragmentGameBinding? = null
    val binding get() = _binding!!
    private val viewmodel:GameFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val animation = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
        _binding = FragmentGameBinding.inflate(inflater,container,false)
        val view = _binding!!.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //val viewmodel = ViewModelProvider(this).get(GameFragmentViewModel::class.java)

        val username = args.username
        val password = args.password
        val game_name = "${username}#${password}"
        val game_field = binding.userGamename
        val scrambled_view:TextView = binding.scrambledWord

        game_field.text = game_name
        val next_button = binding.nextCard
        val hint_button: ImageView = binding.hintButton

        // Live data
        viewmodel.score_return().observe(viewLifecycleOwner, Observer{
            binding.gameScore.text = "SCORE: ${it.toString()}"
        })

        //Animations
        val fade_in = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_in_anime)
        val fade_out = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_out_anim)

        binding.gameScore.text = "SCORE: ${viewmodel.score}"
        scrambled_view.text = viewmodel.scrambled
        binding.guessWordField.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        var word: String = random_word()?.random().toString()

        next_button.setOnClickListener{
            val score_text:TextView = binding.gameScore
            val inputtext: String = binding.guessWordField.text.toString()

            if (inputtext == viewmodel.randomword) {
                word = random_word()?.random().toString()
                viewmodel.randomword = word
                viewmodel.scrambled = scrambled_word_gen(word)
                scrambled_view.startAnimation(fade_in)
                viewmodel.addnum()
                binding.guessWordField.setText("")
                score_text.text = "SCORE: ${viewmodel.score}"
                scrambled_view.text = viewmodel.scrambled
                scrambled_view.startAnimation(fade_out)
                val snackBar = Snackbar.make(view,"Well Done!",Snackbar.LENGTH_SHORT)
                snackBar.apply {
                    setBackgroundTint(resources.getColor(R.color.snack_green))
                    setActionTextColor(resources.getColor(R.color.white))
                    show()
                }
            }
            else{
                binding.guessWordField.error = "Incorrect Answer"
                viewmodel.subnum_one()
                val snackBar = Snackbar.make(view,"Incorrect answer",6000)
                snackBar.apply {
                    setAction("try Again"){
                        this.dismiss()
                    }
                    setBackgroundTint(resources.getColor(R.color.snack_red))
                    setActionTextColor(resources.getColor(R.color.white))
                    show()
                }
            }
        }
        hint_button.setOnClickListener{
            val fade_in = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_in_anime)
            hint_button.startAnimation(fade_in)
            val action = GameFragmentDirections.actionGameFragmentToHintFragment()
            Navigation.findNavController(view).navigate(action)
        }

        //Timer yet to implement
    }
    private fun random_word(): List<String>? {
        val words = context?.resources?.getStringArray(R.array.words)?.toList()
        val filteredwords = words?.shuffled()?.take(5)
        return filteredwords
    }
    private fun scrambled_word_gen(word:String):String{
        var scrambled : String = ""
        val list_words = mutableListOf<Char>()
        for(letters in word){
            list_words.add(letters)
        }
        list_words.shuffle()
        for (chars in list_words){
            scrambled += chars.toString()
        }
        return scrambled
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}