package com.example.testproject.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testproject.base.SharedViewModel
import com.example.testproject.coroutine.Resource
import com.example.testproject.databinding.FragmentHomeBinding
import com.example.testproject.ui.home.serverReq.CategoryAdapter
import com.example.testproject.utils.extension.gone
import com.example.testproject.utils.extension.visible
import com.google.gson.Gson

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val categoryAdapter: CategoryAdapter by lazy { CategoryAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("my_data", "onViewCreated: HomeData: ${sharedViewModel.isHomePageDataAvailable()}")
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            fragment = this@HomeFragment
        }

        binding.swiprefresh.setOnRefreshListener {
            homePageData()
            categoryData()
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) binding.progressBar.visible()
            else binding.progressBar.gone()
        }

        initCategoryRecycler()

        if (sharedViewModel.isHomePageDataAvailable()) {
            // render UI
            Log.d("share_db", "onViewCreated: home page data loaded from CACHE")
        } else homePageData()

        if (sharedViewModel.isCategoryDataAvailable()) {
            categoryAdapter.removeAll()
            categoryAdapter.addNewList(sharedViewModel.categoryData.value?.data)
            Log.d("share_db", "onViewCreated: category data loaded from CACHE")
        } else categoryData()

    }

    private fun initCategoryRecycler() {
        binding.categoryRv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = categoryAdapter
        }
    }

    private fun categoryData() {
        sharedViewModel.getAllCategory().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.swiprefresh.isRefreshing = false
                    homeViewModel.setLoadingStatus(true)
                }
                is Resource.Error -> {
                    homeViewModel.setLoadingStatus(false)
                    Toast.makeText(
                        requireContext(),
                        "Error: ${it.errorMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Success -> {
                    // render UI
                    homeViewModel.setLoadingStatus(false)
                    categoryAdapter.removeAll()
                    categoryAdapter.addNewList(it.data.data)
                    Log.d("share_db", "onViewCreated: category data(share_data):  ${Gson().toJson(it)}")
                }

            }
        }
    }

    private fun homePageData() {
        sharedViewModel.getHomePageData().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.swiprefresh.isRefreshing = false
                    homeViewModel.setLoadingStatus(true)
                }
                is Resource.Error -> {
                    homeViewModel.setLoadingStatus(false)
                    Toast.makeText(
                        requireContext(),
                        "Error: ${it.errorMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Success -> {
                    // render UI
                    homeViewModel.setLoadingStatus(false)
                    Log.d("share_db", "onViewCreated: homepage data(share_data):  ${Gson().toJson(it)}")
                }

            }
        }
    }

}