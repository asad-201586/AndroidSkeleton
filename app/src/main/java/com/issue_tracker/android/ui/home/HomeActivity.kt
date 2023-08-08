package com.issue_tracker.android.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.issue_tracker.android.base.KotlinBaseActivity
import com.issue_tracker.android.coroutine.Resource
import com.issue_tracker.android.databinding.ActivityHomeBinding
import com.issue_tracker.android.ui.home.adapter.IssueListAdapter
import com.issue_tracker.android.ui.home.viewmodel.HomeViewModel
import com.issue_tracker.android.utils.SharedPreferenceUtils
import com.issue_tracker.android.utils.extension.customToast
import com.issue_tracker.android.utils.extension.gone
import com.issue_tracker.android.utils.extension.visible
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : KotlinBaseActivity() {
    private var repoName: String? = null
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    private val issueListAdapter: IssueListAdapter by lazy { IssueListAdapter() }

    @Inject
    lateinit var pref: SharedPreferenceUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initListeners()
        fetchData()
    }

    private fun initListeners() {
        binding.swipeRefresh.setOnRefreshListener { fetchData() }
    }

    private fun fetchData() {
        binding.swipeRefresh.isRefreshing = false
        viewModel.getIssueList(1,repoName?:"").observe(this) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressbar.visible()
                    Timber.i("remote_data ------------ Loading...")
                }
                is Resource.Error -> {
                    binding.progressbar.gone()
                    binding.textNoDataFound.visible()
                    Timber.i("remote_data ------------ Error: ${it.errorMessage}")
                }
                is Resource.Success -> {
                    binding.textNoDataFound.gone()
                    binding.progressbar.gone()
                    //Timber.i("remote_data ------------ Success: ${Gson().toJson(it)}")
                    if (it.data.isEmpty()) {
                        customToast("No data found")
                        binding.textNoDataFound.visible()
                        return@observe
                    }
                    issueListAdapter.submitList(it.data)
                }
                else -> {}
            }
        }
    }

    private fun initViews() {
        repoName = intent.getStringExtra("repo_name")
        binding.issueRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = issueListAdapter
        }
        binding.textRepoName.text = repoName
    }
}