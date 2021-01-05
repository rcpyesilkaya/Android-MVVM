package com.recepyesilkaya.koin_sample.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.recepyesilkaya.koin_sample.data.model.Pray
import com.recepyesilkaya.koin_sample.data.repository.PrayRepository
import com.recepyesilkaya.koin_sample.util.Resource
import com.recepyesilkaya.koin_sample.util.Status
import kotlinx.coroutines.flow.onStart

class HomeViewModel(
    private val prayRepository: PrayRepository,
) : ViewModel() {

    /*private  val db = RoomDatabase.getDatabase(application, viewModelScope)
    privatev al prayDao = db.prayDao()
    private val apiService = RetrofitClient.getRetrofit().create(APIService::class.java)
    private val prayRepository = PrayRepository(apiService,prayDao)*/


    fun getPrayData(id: String, statusDataLocal: Boolean): LiveData<Resource<List<Pray>>> =
        prayRepository.getPrayData(id, statusDataLocal)
            .onStart { emit(Resource.loading(data = null)) }
            .asLiveData()

    private val _loadingValue = MutableLiveData<Boolean>()
    val loadingValue: LiveData<Boolean>
        get() = _loadingValue

    private val _successValue = MutableLiveData<Boolean>()
    val successValue: LiveData<Boolean>
        get() = _successValue

    private val _errorValue = MutableLiveData<Boolean>()
    val errorValue: LiveData<Boolean>
        get() = _errorValue

    private val _prays = MutableLiveData<List<Pray>>()
    val prays: LiveData<List<Pray>>
        get() = _prays

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun resourceStatusData(resource: Resource<List<Pray>>) {
        when (resource.status) {
            Status.LOADING -> {
                Log.e("Data", "Loading")
                _loadingValue.value = true
                _successValue.value = false
                _errorValue.value = false
            }
            Status.SUCCESS -> {
                Log.e("Data", "Success")
                resource.data?.let { prayList -> _prays.value = prayList }
                _loadingValue.value = false
                _successValue.value = true
                _errorValue.value = false
            }
            Status.ERROR -> {
                resource.message
                Log.e("Data", "Error")
                _error.value = "Veriler Yüklenirken Hata Oluştu! ${resource.message} "
                _loadingValue.value = false
                _successValue.value = false
                _errorValue.value = true
            }
        }
    }

}