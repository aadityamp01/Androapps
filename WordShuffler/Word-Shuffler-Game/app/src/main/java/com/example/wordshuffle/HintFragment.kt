package com.example.wordshuffle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import com.example.wordshuffle.databinding.FragmentHintBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HintFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentHintBinding? = null
    private val binding get() = _binding!!

    private val viewmodel:GameFragmentViewModel by activityViewModels()
    //Animations
    private val fadein: Animation by lazy{AnimationUtils.loadAnimation(requireContext(),R.anim.fade_in_anime)}
    private val fadeout: Animation by lazy{AnimationUtils.loadAnimation(requireContext(),R.anim.fade_out_anim)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHintBinding.inflate(inflater,container,false)
        val view = _binding!!.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnAgree.setOnClickListener {
            viewmodel.subnum_five()
            val word = firstLastLetter(viewmodel.randomword.toString())
            binding.textHeader1.text = word
            it.apply{
                visibility = View.INVISIBLE
                binding.textInfo2.visibility = View.GONE
            }
        }
        binding.closePopup.setOnClickListener {
            dismiss()
        }
    }

    private fun firstLastLetter(word:String): String {
        val name: String = word
        val arr = mutableListOf<String>()
        for(letters in name){arr.add(letters.toString())}
        return ("Starts with '${arr[0]}' Ends with '${arr[arr.size - 1]}' ")
    }

}