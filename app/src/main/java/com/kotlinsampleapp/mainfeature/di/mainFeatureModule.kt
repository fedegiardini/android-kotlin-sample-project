package com.kotlinsampleapp.mainfeature.di

import com.kotlinsampleapp.mainfeature.data.MainRepository
import com.kotlinsampleapp.mainfeature.usecase.SearchItemsByQueryUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

/**
 * @author federico.giardini
 */
@ExperimentalCoroutinesApi
val mainFeatureModule = module {
    single { MainRepository(get()) }

    factory { SearchItemsByQueryUseCase(get()) }
}