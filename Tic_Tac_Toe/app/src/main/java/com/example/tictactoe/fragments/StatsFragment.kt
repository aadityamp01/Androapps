package com.example.tictactoe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.tictactoe.ViewModel.StatsViewModel
import com.example.tictactoe.databinding.FragmentStatsBinding
import com.google.firebase.auth.FirebaseAuth

class StatsFragment : Fragment() {

    lateinit var binding : FragmentStatsBinding
    lateinit var auth : FirebaseAuth
    lateinit var viewModel : StatsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatsBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(this).get(StatsViewModel::class.java)
        auth = FirebaseAuth.getInstance()
        viewModel.getUser()
        viewModel.UserObserve().observe(viewLifecycleOwner, Observer {
            var winPercent = (it!!.noOWins?.div(it!!.totalMatches!!))?.times(100)
            binding.tvWins.text = it!!.noOWins?.toInt().toString()
            binding.tvLosses.text = it!!.noOLoss?.toInt().toString()
            binding.tvTotalGames.text = it!!.totalMatches.toString()
            binding.tvWinPercent.text = "${winPercent} %"
        })
        Glide.with(this).load(auth.currentUser?.photoUrl).into(binding.ProfilePic)
        binding.tvName.text = auth.currentUser?.displayName
        return binding.root
    }


}