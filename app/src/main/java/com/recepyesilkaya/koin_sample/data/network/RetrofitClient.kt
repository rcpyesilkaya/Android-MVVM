package com.recepyesilkaya.koin_sample.data.network

import com.recepyesilkaya.koin_sample.util.AppInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        @Volatile
        private var INSTANCE: Retrofit? = null

        fun getRetrofit(): Retrofit {
            synchronized(this) {
                INSTANCE?.let {
                    return it
                }
                INSTANCE =
                    Retrofit.Builder()
                        .baseUrl(AppInfo.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                return INSTANCE as Retrofit
            }
        }
    }
}