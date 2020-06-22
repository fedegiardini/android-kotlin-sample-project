package com.kotlinsampleapp.di

import com.kotlinsampleapp.mainfeature.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author federico.giardini
 */
@ExperimentalCoroutinesApi
val viewModelsModule = module {
    viewModel { MainViewModel(get()) }
}