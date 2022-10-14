package com.example.tictactoe.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.R
import com.example.tictactoe.SignInActivity
import com.example.tictactoe.databinding.FragmentMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainFragment : Fragment() {
    lateinit var binding : FragmentMainBinding
    lateinit var auth : FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        auth = FirebaseAuth.getInstance()
        binding.btnComp.setOnClickListener {
          findNavController().navigate(R.id.action_mainFragment_to_shapeSelectorFragment)
        }
        binding.btnPlayer.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_playerFragment)
        }
        binding.FragmentMain2.text = "Welcome ${auth.currentUser?.displayName}"
        binding.btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(context,SignInActivity::class.java))
            requireActivity().finish()
        }
        return binding.root
    }

}