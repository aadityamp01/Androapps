package com.example.tictactoe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.databinding.FragmentShapeSelectorBinding


class ShapeSelectorFragment : Fragment() {

    lateinit var binding : FragmentShapeSelectorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShapeSelectorBinding.inflate(layoutInflater,container,false)
        binding.btnX.setOnClickListener {
            val direction =
               ShapeSelectorFragmentDirections.actionShapeSelectorFragmentToComputerFragment(
                    "X"
                )
            findNavController().navigate(direction)
        }
        binding.btnO.setOnClickListener {
            val direction =
                ShapeSelectorFragmentDirections.actionShapeSelectorFragmentToComputerFragment(
                    "O"
                )
            findNavController().navigate(direction)
        }
        return binding.root
    }

}