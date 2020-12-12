package com.recepyesilkaya.koin_sample.di

import com.recepyesilkaya.koin_sample.data.repository.PrayRepository
import com.recepyesilkaya.koin_sample.view.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel(get()) }

    single { PrayRepository(get(), get()) }

}