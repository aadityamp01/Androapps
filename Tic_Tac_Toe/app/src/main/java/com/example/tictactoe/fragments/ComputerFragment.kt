package com.example.tictactoe.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.R
import com.example.tictactoe.ViewModel.ComputerViewModel
import com.example.tictactoe.databinding.FragmentComputerBinding
import com.example.tictactoe.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class ComputerFragment : Fragment() {

    lateinit var binding : FragmentComputerBinding
    lateinit var viewModel : ComputerViewModel
    lateinit var auth : FirebaseAuth
    var currCell = 0
    var compCell = 0
    var playerTurn = true
    var currUser = 1
    var player = ArrayList<Int>()
    var comp = ArrayList<Int>()
    var filled = ArrayList<Int>()
   lateinit var shapeP : String
    lateinit var shapeC : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ComputerViewModel::class.java)
        binding = FragmentComputerBinding.inflate(layoutInflater,container,false)
        auth = FirebaseAuth.getInstance()
         shapeP = ComputerFragmentArgs.fromBundle(requireArguments()).shape

        if (shapeP == "X"){
            shapeC = "O"
        }else{
            shapeC ="X"
        }

        viewModel.playerObserve().observe(viewLifecycleOwner , Observer {
            player =it
            Log.d("@@FromPlayerObsever" , it.toString())
            for(index in player){
                var btn = when(index){
                    1 -> binding.btn1
                    2 -> binding.btn2
                    3 -> binding.btn3
                    4 -> binding.btn4
                    5 -> binding.btn5
                    6 -> binding.btn6
                    7 -> binding.btn7
                    8 -> binding.btn8
                    9 -> binding.btn9
                    else -> {binding.btn1}
                }
                btn.text = shapeP
                btn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
                    R.color.matteGreen
                ))
            }
            currUser = 2

        })
        viewModel.computerObserve().observe(viewLifecycleOwner, Observer {
            Log.d("@@FromComputerObserver" , it.toString())
            comp = it
            for(index in  comp){
                var btn = when(index){
                    1 -> binding.btn1
                    2 -> binding.btn2
                    3 -> binding.btn3
                    4 -> binding.btn4
                    5 -> binding.btn5
                    6 -> binding.btn6
                    7 -> binding.btn7
                    8 -> binding.btn8
                    9 -> binding.btn9
                    else -> {binding.btn1}
                }
                btn.text = shapeC
                currUser = 1
                btn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
                    R.color.matteRed
                ))
            }

        })
        viewModel.resultObserve().observe(viewLifecycleOwner, Observer {
            if(it == 1){
                Toast.makeText(context, "You won wow :0", Toast.LENGTH_SHORT).show()
                viewModel.addUser(UserModel(auth.uid!!,1.0,0.0,1))
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    reset()
                }, 1000)
            }else if(it == 2){
                Toast.makeText(context, "You lost. Better luck next time", Toast.LENGTH_SHORT).show()
                viewModel.addUser(UserModel(auth.uid!!,0.0,1.0,1))
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    reset()
                }, 1000)
            }else if(it ==3){
                Toast.makeText(context, "Draw", Toast.LENGTH_SHORT).show()
                viewModel.addUser(UserModel(auth.uid!!,0.0,0.0,1))
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    reset()
                }, 1000)
            }
        })

        viewModel.playerTurnObserve().observe(viewLifecycleOwner, Observer {
            playerTurn = it
            if(playerTurn){
                binding.btnTurn.text = "Your Turn"
                binding.btnTurn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
                    R.color.matteGreen
                ))
            }else{
                binding.btnTurn.text = "Please Wait"
                binding.btnTurn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
                    R.color.matteRed
                ))
            }
            Log.d("@@Turn" , playerTurn.toString())
        })


        binding.btn1.setOnClickListener {
            Log.d("@@PlayerTurn" , playerTurn.toString())
            if(playerTurn){
                currCell = 1
                viewModel.playerTurn(currCell)
            }
        }
        binding.btn2.setOnClickListener {
            if(playerTurn){
                currCell = 2
                viewModel.playerTurn(currCell)
            }
        }
        binding.btn3.setOnClickListener {
            if(playerTurn){
                currCell = 3
                viewModel.playerTurn(currCell)
            }
        }
        binding.btn4.setOnClickListener {
            if(playerTurn){
                currCell = 4
                viewModel.playerTurn(currCell)
            }
        }
        binding.btn5.setOnClickListener {
            if(playerTurn){
                currCell = 5
                viewModel.playerTurn(currCell)
            }
        }
        binding.btn6.setOnClickListener {
            if(playerTurn){
                currCell = 6
                viewModel.playerTurn(currCell)
            }
        }
        binding.btn7.setOnClickListener {
            if(playerTurn){
                currCell = 7
                viewModel.playerTurn(currCell)
            }
        }
        binding.btn8.setOnClickListener {
            if(playerTurn){
                currCell = 8
                viewModel.playerTurn(currCell)
            }
        }
        binding.btn9.setOnClickListener {
            if(playerTurn){
                currCell = 9
                viewModel.playerTurn(currCell)
            }
        }

        binding.btnReset.setOnClickListener {
            reset()
        }
        binding.btnStats.setOnClickListener {
            findNavController().navigate(R.id.action_computerFragment_to_statsFragment)
        }

        return binding.root
    }
    fun reset(){
       binding.btn1.text = ""
        binding.btn2.text = ""
        binding.btn3.text = ""
        binding.btn4.text = ""
        binding.btn5.text = ""
        binding.btn6.text = ""
        binding.btn7.text = ""
        binding.btn8.text = ""
        binding.btn9.text = ""
        binding.btn1.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
            R.color.design_default_color_primary
        ))
        binding.btn2.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
            R.color.design_default_color_primary
        ))
        binding.btn3.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
            R.color.design_default_color_primary
        ))
        binding.btn4.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
            R.color.design_default_color_primary
        ))
        binding.btn5.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
            R.color.design_default_color_primary
        ))
        binding.btn6.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
            R.color.design_default_color_primary
        ))
        binding.btn7.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
            R.color.design_default_color_primary
        ))
        binding.btn8.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
            R.color.design_default_color_primary
        ))
        binding.btn9.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
            R.color.design_default_color_primary
        ))
        filled.clear()
        player.clear()
        comp.clear()
        playerTurn = true
        viewModel.reset()

    }
}
