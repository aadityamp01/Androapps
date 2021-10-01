package com.example.wordshuffle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.wordshuffle.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_welcome, container, false)
        _binding = FragmentWelcomeBinding.inflate(inflater,container,false)
        val view = _binding!!.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val theme_button = binding.changeTheme
        theme_button.setOnClickListener{
            change_theme(theme_button)
        }
        val start_button = binding.materialButton
        start_button.setOnClickListener {
            val username: String = binding.personName.text.toString()
            val password: String = binding.personPassword.text.toString()
            if (username!="" && password!="") {
                val logo_transition = FragmentNavigatorExtras(binding!!.imageView to "game_profile_icon")
                //val action = WelcomeFragmentDirections.actionWelcomeFragmentToGameFragment(username, password)

                val bundle = bundleOf("username" to username,"password" to password)
                Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_gameFragment,bundle,null,logo_transition)
            }
            else{
                if (username==""){ binding.editPersonNameLayout.error = "Required Fireld" }
                else if (password == ""){binding.editPersonPasswordLayout.error = "Required Field"}
                else{
                    binding.editPersonNameLayout.error = "Required Fireld"
                    binding.editPersonPasswordLayout.error = "Required Field"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun change_theme(theme_button:ImageView){
        val roate_anim = AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_fortyfive_anim)

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            theme_button.startAnimation(roate_anim)
            //theme_button.setImageResource(R.drawable.ic_round_wb_sunny_24)
        }
        else{

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            theme_button.startAnimation(roate_anim)
            //theme_button.setImageResource(R.drawable.ic_round_bedtime_24)
        }
    }

}