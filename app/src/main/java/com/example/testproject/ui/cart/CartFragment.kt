package com.example.testproject.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.testproject.base.SharedViewModel
import com.example.testproject.databinding.FragmentCartBinding
import com.google.gson.Gson
import com.orhanobut.logger.Logger

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            fragment = this@CartFragment
        }

        Log.d("my_data", "onViewCreated: isHomePageDataAvailable: ${sharedViewModel.isHomePageDataAvailable()}")
        if (sharedViewModel.isHomePageDataAvailable()) {
            //Log.d("share_db", "onViewCreated: SHARE -> ${Gson().toJson(sharedViewModel.homeData)}")
            Logger.json(Gson().toJson(sharedViewModel.homeData))
        }

    }
}