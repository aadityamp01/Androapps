package com.example.passwordmanager.Fragments

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passwordmanager.Data.Key
import com.example.passwordmanager.RecyclerViewAdapter
import com.example.passwordmanager.ViewModels.KeyViewModel
import com.example.passwordmanager.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment(),RecyclerViewAdapter.RVInterface {

    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: KeyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        Log.d("view crash","view created")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.hideKeyboard()
        val adapter = RecyclerViewAdapter(this)
        val recyclerView = binding.keyRecyclerView

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        mViewModel.listKeys().observe(viewLifecycleOwner, Observer {
            Log.d("database_error",it.toString())
            adapter.notifyChanges(it)
        })

        binding.floatingActionButton.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToAddFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun onViewClick(key: Key) {
        val action = HomeFragmentDirections.actionHomeFragmentToAddFragment(key)
        Navigation.findNavController(requireView()).navigate(action)
    }

    override fun onClipboardClick(key: Key) {
        val clipboard = ContextCompat.getSystemService(requireContext(),ClipboardManager::class.java)
        val clip = ClipData.newPlainText("label", key.password)
        clipboard?.setPrimaryClip(clip)
        Snackbar.make(requireView(),"Copied to Clipboard",Snackbar.LENGTH_SHORT).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("ServiceCast")
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}