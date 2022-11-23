package com.example.testproject.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.testproject.base.CommonViewModel
import com.example.testproject.coroutine.Resource
import com.example.testproject.databinding.FragmentHomeBinding
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.log

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val sharedViewModel: CommonViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            fragment = this@HomeFragment
        }

        Log.d("share_db", "onViewCreated: homeFragment")
        sharedViewModel.getHomePageData().observe(viewLifecycleOwner) {
            if (it is Resource.Success) {
                Log.d("share_db", "onViewCreated: data:  ${Gson().toJson(it)}")
            }
        }

        homeViewModel.getHomePageData().observe(viewLifecycleOwner) {
            if (it is Resource.Success) {
                Log.d("share_db", "onViewCreated: data(home):  ${Gson().toJson(it)}")
            }
            if (it is Resource.Error) {
                Log.d("share_db", "onViewCreated: error: ${it.errorMessage}")
            }
            if (it is Resource.Loading) Log.d("share_db", "onViewCreated: loading...")
        }

    }

}