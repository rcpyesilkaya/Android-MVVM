package com.recepyesilkaya.koin_sample.data.network

import com.recepyesilkaya.koin_sample.data.model.Pray
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("vakitler/{id}")
    suspend fun getData(@Path("id") id: String): Response<List<Pray>>
}