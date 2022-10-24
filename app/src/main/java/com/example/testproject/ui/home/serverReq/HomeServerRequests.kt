package com.example.testproject.ui.home.serverReq

import androidx.lifecycle.LifecycleOwner
import com.example.testproject.coroutine.Resource
import com.example.testproject.databinding.ActivityHomeBinding
import com.example.testproject.ui.home.HomeActivity
import com.example.testproject.ui.home.viewmodel.HomeViewModel
import com.google.gson.Gson
import com.orhanobut.logger.Logger

class HomeServerRequests(
    private val binding: ActivityHomeBinding,
    private val viewModel: HomeViewModel,
    private val activity: HomeActivity,
    private val lifecycleOwner: LifecycleOwner
) {

    fun getHomePageData(customerId: String) {
        viewModel.getHomePageData(customerId).observe(lifecycleOwner) {
            when (it) {
                is Resource.Loading -> activity.showProgress()
                is Resource.Error -> {
                    activity.hideProgress()
                    binding.textAlert.text = it.errorMessage
                }
                is Resource.Success -> {
                    activity.hideProgress()
                    binding.textAlert.text = "Data found"
                    Logger.json(Gson().toJson(it.data))
                }
            }
        }
    }

}