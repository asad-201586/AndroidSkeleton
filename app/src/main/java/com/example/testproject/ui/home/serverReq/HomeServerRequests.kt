package com.example.testproject.ui.home.serverReq

import androidx.lifecycle.LifecycleOwner
import com.example.testproject.coroutine.Resource
import com.example.testproject.databinding.ActivityMainBinding
import com.example.testproject.ui.MainActivity
import com.example.testproject.ui.home.HomeViewModel
import com.google.gson.Gson
import com.orhanobut.logger.Logger

class HomeServerRequests(
    private val binding: ActivityMainBinding,
    private val viewModel: HomeViewModel,
    private val activity: MainActivity,
    private val lifecycleOwner: LifecycleOwner
) {

//    fun getHomePageData(customerId: String) {
//        viewModel.getHomePageData(customerId).observe(lifecycleOwner) {
//            when (it) {
//                is Resource.Loading -> activity.showProgress()
//                is Resource.Error -> {
//                    activity.hideProgress()
//                    binding.textAlert.text = it.errorMessage
//                }
//                is Resource.Success -> {
//                    activity.hideProgress()
//                    binding.textAlert.text = "Data found"
//                    Logger.json(Gson().toJson(it.data))
//                }
//            }
//        }
//    }

}