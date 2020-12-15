package com.recepyesilkaya.koin_sample.di

import com.recepyesilkaya.koin_sample.BuildConfig
import com.recepyesilkaya.koin_sample.data.network.APIService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { retrofitClient<APIService>(get()) }
    single { getRetrofit() }
}

inline fun <reified T> retrofitClient(retrofit: Retrofit): T = retrofit.create(T::class.java)

fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}