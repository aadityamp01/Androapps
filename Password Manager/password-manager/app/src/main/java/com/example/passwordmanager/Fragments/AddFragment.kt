package com.example.passwordmanager.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.passwordmanager.Data.Key
import com.example.passwordmanager.R
import com.example.passwordmanager.ViewModels.KeyViewModel
import com.example.passwordmanager.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: KeyViewModel by activityViewModels()
    private val args: AddFragmentArgs by navArgs()
    private var keyId:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val argsKey:Key? = args.key
        if (argsKey==null) {
            binding.edittextLayout4.setStartIconOnClickListener {
                val generatedPassword = passwordGen()
                binding.passwordInput.setText(generatedPassword)
            }
        }else{
            val key = argsKey!!
            binding.topBarTitle.setText(R.string.update_key)
            binding.edittextLayout4.startIconDrawable?.setVisible(false,false)
            binding.apply {
                keyId = key.id
                accountInput.setText(key.account)
                passwordInput.setText(key.password)
                usernameInput.setText(key.username)
                additionalInput.setText(key.additional)
            }
            binding.deleteKey.visibility = View.VISIBLE
        }

        binding.backButton1.setOnClickListener {
            val action = AddFragmentDirections.actionAddFragmentToHomeFragment()
            Navigation.findNavController(view).navigate(action)
        }

        binding.floatingActionButton2.setOnClickListener {
            val account = binding.accountInput.text.toString()
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val additionalInfo = binding.additionalInput.text.toString()

            val keyArray = listOf<String>(account,username,password)

//            Toast.makeText(context,"$account,$username,$password",Toast.LENGTH_SHORT).show()
            if (keyArray.all{value->
                value != ""
                }){
                val key = Key(keyId,account,username,additionalInfo,password)
                mViewModel.addKey(key)

                //TODO: Add snackbar for adding & deleting
                val action = AddFragmentDirections.actionAddFragmentToHomeFragment()
                Navigation.findNavController(view).navigate(action)
            }else{
                Toast.makeText(context,"failure",Toast.LENGTH_SHORT).show()
                for (item in keyArray){
                    if (item==""){
                        when(keyArray.indexOf(item)){
                            0 -> binding.accountInput.error = "Required Field"
                            1-> binding.usernameInput.error = "Required Field"
                            else -> binding.passwordInput.error = "Required Field"
                        }
                    }
                }
            }

        }

        binding.deleteKey.setOnClickListener {
            val account = binding.accountInput.text.toString()
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val additionalInfo = binding.additionalInput.text.toString()

            val key = Key(keyId,account,username,additionalInfo,password)
            mViewModel.deleteKey(key)
            val action = AddFragmentDirections.actionAddFragmentToHomeFragment()
            Navigation.findNavController(view).navigate(action)
        }

        binding.accountInput.requestFocus()
        view.showKeyboard()
    }
    private fun passwordGen():String {
        val length = (8..12).random()
        val specialChar = listOf<Char>('@','#','*','$')
        val passCollection: List<Char> = ('a'..'z')+('A'..'Z')+('1'..'9')+specialChar

        return (1..length)
            .map{ kotlin.random.Random.nextInt(0, passCollection.size) }
            .map(passCollection::get)
            .joinToString("")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("ServiceCast")
    fun View.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0)
    }
    @SuppressLint("ServiceCast")
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


}