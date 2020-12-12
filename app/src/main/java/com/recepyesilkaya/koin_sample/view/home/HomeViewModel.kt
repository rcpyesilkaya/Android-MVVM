package com.recepyesilkaya.koin_sample.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.recepyesilkaya.koin_sample.data.model.Pray
import com.recepyesilkaya.koin_sample.data.repository.PrayRepository
import com.recepyesilkaya.koin_sample.util.Resource

class HomeViewModel(
    private val prayRepository: PrayRepository,
) : ViewModel() {

    /*private  val db = RoomDatabase.getDatabase(application, viewModelScope)
    privatev al prayDao = db.prayDao()
    private val apiService = RetrofitClient.getRetrofit().create(APIService::class.java)
    private val prayRepository = PrayRepository(apiService,prayDao)*/

    fun getPrayData(id: String, statusDataLocal: Boolean): LiveData<Resource<List<Pray>>> =
        prayRepository.getPrayData(id, statusDataLocal).asLiveData(viewModelScope.coroutineContext)

}