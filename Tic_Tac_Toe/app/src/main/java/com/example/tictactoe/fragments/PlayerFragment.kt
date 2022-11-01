package com.example.tictactoe.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.R
import com.example.tictactoe.ViewModel.PlayerViewModel
import com.example.tictactoe.databinding.FragmentPlayerBinding
import java.util.*


class PlayerFragment : Fragment() {

    lateinit var binding : FragmentPlayerBinding
    lateinit var viewModel : PlayerViewModel
    var currCell = 0
    var currUser = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var filled = ArrayList<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
        binding = FragmentPlayerBinding.inflate(layoutInflater,container,false)

        viewModel.player1Observer().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            player1 = it
            for(index in player1){
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
                btn.text = "X"
                btn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
                    R.color.matteGreen
                ))
            }
        })
        viewModel.player2Observer().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            player2 = it
            for(index in player2){
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
                btn.text = "O"
                btn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
                    R.color.matteRed
                ))
            }
        })
        viewModel.playerTurnObserver().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if(it){
                binding.btnTurn.text = "Player One Turn"
                binding.btnTurn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
                    R.color.matteGreen
                ))
            }else{
                binding.btnTurn.text = "Player Two Turn"
                binding.btnTurn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),
                    R.color.matteRed
                ))
            }
        })
        viewModel.resultObserver().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if(it == 1){
                Toast.makeText(context, "Player One, WON", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    reset()
                }, 500)
            }else if(it == 2){
                Toast.makeText(context, "Player Two, WON", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    reset()
                }, 500)
            }else if(it ==3){
                Toast.makeText(context, "Draw", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    reset()
                }, 500)
            }
        })

        binding.btn1.setOnClickListener {
            currCell = 1
               viewModel.move(currCell)
        }
        binding.btn2.setOnClickListener { currCell = 2
            viewModel.move(currCell)
        }
        binding.btn3.setOnClickListener { currCell = 3
            viewModel.move(currCell)
        }
        binding.btn4.setOnClickListener { currCell = 4
            viewModel.move(currCell)
        }
        binding.btn5.setOnClickListener { currCell = 5
            viewModel.move(currCell)
        }
        binding.btn6.setOnClickListener { currCell = 6
            viewModel.move(currCell)
        }
        binding.btn7.setOnClickListener { currCell = 7
            viewModel.move(currCell)
        }
        binding.btn8.setOnClickListener { currCell = 8
            viewModel.move(currCell)
        }
        binding.btn9.setOnClickListener { currCell = 9
            viewModel.move(currCell)
        }
        binding.btnReset.setOnClickListener {
            reset()
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
        player1.clear()
        player2.clear()
        viewModel.reset()
        currUser = 1
    }

}