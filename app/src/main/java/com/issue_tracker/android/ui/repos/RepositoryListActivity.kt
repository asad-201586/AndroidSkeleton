package com.issue_tracker.android.ui.repos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.gson.Gson
import com.issue_tracker.android.base.KotlinBaseActivity
import com.issue_tracker.android.coroutine.Resource
import com.issue_tracker.android.databinding.ActivityRepositoryListBinding
import com.issue_tracker.android.utils.extension.customToast
import com.issue_tracker.android.utils.extension.gone
import com.issue_tracker.android.utils.extension.visible
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class RepositoryListActivity : KotlinBaseActivity() {

    private lateinit var binding: ActivityRepositoryListBinding
    private val repoViewModel: ReposViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoryListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchData()
    }

    private fun fetchData() {
        repoViewModel.getRepos(1).observe(this) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressbar.visible()
                    Timber.i("remote_data ------------ Loading...")
                }
                is Resource.Error -> {
                    binding.progressbar.gone()
                    Timber.i("remote_data ------------ Error: ${it.errorMessage}")
                }
                is Resource.Success -> {
                    binding.progressbar.gone()
                    Timber.i("remote_data ------------ Success: ${Gson().toJson(it)}")
                    if (it.data.isEmpty()) {
                        customToast("No data found")
                        return@observe
                    }
                    customToast("data found")
                }
                else -> {}
            }
        }
    }
}