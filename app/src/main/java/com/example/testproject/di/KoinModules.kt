package com.example.testproject.di

import com.example.testproject.ui.home.repo.HomeRepo
import com.example.testproject.ui.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModels = module {
    viewModel { HomeViewModel() }
}

val common = module {
    single { HomeRepo() }
}