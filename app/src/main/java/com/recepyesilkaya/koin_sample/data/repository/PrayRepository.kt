package com.recepyesilkaya.koin_sample.data.repository

import android.util.Log
import com.recepyesilkaya.koin_sample.data.local.PrayDao
import com.recepyesilkaya.koin_sample.data.model.Pray
import com.recepyesilkaya.koin_sample.data.network.APIService
import com.recepyesilkaya.koin_sample.util.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class PrayRepository(private val apiService: APIService, private val prayDao: PrayDao) {

    fun getPrayData(id: String, statusDataLocal: Boolean): Flow<Resource<List<Pray>>> = flow {
        try {
            if (statusDataLocal) {
                val response = prayDao.getAllPrayTime()
                response?.let {
                    emit(Resource.success(it))
                    Log.e("data", "Yerel ")
                }
            } else {
                val response = apiService.getData(id)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.e("data", "Uzak Sunucu")
                        emit(Resource.success(it))
                        insertData(it)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.error(data = null, message = e.message ?: "Hata"))
        }
    }
    private fun insertData(prayList: List<Pray>) {
        GlobalScope.launch {
            prayDao.deleteData()
            prayDao.insertPray(*prayList.toTypedArray())
        }
    }
}
