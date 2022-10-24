package com.example.testproject.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.testproject.base.KotlinBaseActivity
import com.example.testproject.databinding.ActivityHomeBinding
import com.example.testproject.ui.home.serverReq.HomeServerRequests
import com.example.testproject.ui.home.viewmodel.HomeViewModel

class HomeActivity : KotlinBaseActivity() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    private lateinit var serverReq: HomeServerRequests
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init server request
        serverReq = HomeServerRequests(binding,viewModel,this,this)

        // get home page data
        serverReq.getHomePageData("79744")

    }
}