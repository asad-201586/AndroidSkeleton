package com.issue_tracker.android.ui.repos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.issue_tracker.android.base.KotlinBaseActivity
import com.issue_tracker.android.coroutine.Resource
import com.issue_tracker.android.databinding.ActivityRepositoryListBinding
import com.issue_tracker.android.ui.home.adapter.ReposAdapterPaged
import com.issue_tracker.android.utils.extension.customToast
import com.issue_tracker.android.utils.extension.gone
import com.issue_tracker.android.utils.extension.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class RepositoryListActivity : KotlinBaseActivity() {

    private lateinit var binding: ActivityRepositoryListBinding
    private val repoViewModel: ReposViewModel by viewModels()
    private val reposAdapter: ReposAdapterPaged by lazy { ReposAdapterPaged() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoryListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initListeners()
        initObservers()
        fetchData()
    }

    private fun initObservers() {
        repoViewModel.isLoading.observe(this) {
            binding.progressbar.apply {
                if (it) visible() else gone()
            }
        }
    }

    private fun initListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            fetchData()
        }
    }

    private fun initViews() {
        binding.reposRV.apply {
            layoutManager = LinearLayoutManager(this@RepositoryListActivity)
            adapter = reposAdapter
        }
    }

    private fun fetchData() {
        binding.swipeRefresh.isRefreshing = false
        reposAdapter.submitData(lifecycle, PagingData.empty())
        val pagingData = repoViewModel.getReposPaged().distinctUntilChanged()

        lifecycleScope.launch {
            pagingData.collect {
                Timber.i("paging_adapter ------------$it")
                reposAdapter.submitData(it)
            }
        }
    }
}