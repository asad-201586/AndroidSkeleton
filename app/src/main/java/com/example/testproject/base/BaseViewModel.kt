package com.example.testproject.base

import androidx.lifecycle.ViewModel
import com.example.testproject.ui.home.HomeRepo
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseViewModel : ViewModel(), KoinComponent {
    private val homeRepository: HomeRepo by inject()
    fun getHomeRepo() = homeRepository
}