package com.recepyesilkaya.koin_sample.di

import com.recepyesilkaya.koin_sample.data.network.APIService
import com.recepyesilkaya.koin_sample.util.AppInfo
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { retrofitClient<APIService>() }

}

inline fun <reified T> retrofitClient(): T {
    val retrofit =
        Retrofit.Builder()
            .baseUrl(AppInfo.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    return retrofit.create(T::class.java)
}