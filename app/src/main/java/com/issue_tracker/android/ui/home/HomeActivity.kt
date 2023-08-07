package com.issue_tracker.android.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import com.google.gson.Gson
import com.issue_tracker.android.base.KotlinBaseActivity
import com.issue_tracker.android.coroutine.Resource
import com.issue_tracker.android.databinding.ActivityHomeBinding
import com.issue_tracker.android.ui.home.viewmodel.HomeViewModel
import com.issue_tracker.android.utils.PrefKeys
import com.issue_tracker.android.utils.SharedPreferenceUtils
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : KotlinBaseActivity() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var pref: SharedPreferenceUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.getIssueList(1).observe(this) {
            when (it) {
                is Resource.Loading -> Timber.i("remote_data ------------ Loading...")
                is Resource.Error -> Timber.i("remote_data ------------ Error: ${it.errorMessage}")
                else -> {
                    Timber.i("remote_data ------------ Success: ${Gson().toJson(it)}")
                }
            }
        }
    }
}