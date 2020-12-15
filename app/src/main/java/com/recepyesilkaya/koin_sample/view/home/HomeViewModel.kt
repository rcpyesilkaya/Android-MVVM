package com.recepyesilkaya.koin_sample.view.home

import android.util.Log
import androidx.lifecycle.*
import com.recepyesilkaya.koin_sample.data.model.Pray
import com.recepyesilkaya.koin_sample.data.repository.PrayRepository
import com.recepyesilkaya.koin_sample.util.Resource
import com.recepyesilkaya.koin_sample.util.Status

class HomeViewModel(
    private val prayRepository: PrayRepository,
) : ViewModel() {

    /*private  val db = RoomDatabase.getDatabase(application, viewModelScope)
    privatev al prayDao = db.prayDao()
    private val apiService = RetrofitClient.getRetrofit().create(APIService::class.java)
    private val prayRepository = PrayRepository(apiService,prayDao)*/

    fun getPrayData(id: String, statusDataLocal: Boolean): LiveData<Resource<List<Pray>>> =
        prayRepository.getPrayData(id, statusDataLocal).asLiveData(viewModelScope.coroutineContext)

    private var _loadingValue = MutableLiveData<Boolean>()
    val loadingValue: LiveData<Boolean>
        get() = _loadingValue

    private var _successValue = MutableLiveData<Boolean>()
    val successValue: LiveData<Boolean>
        get() = _successValue

    private var _errorValue = MutableLiveData<Boolean>()
    val errorValue: LiveData<Boolean>
        get() = _errorValue

    private var _prays = MutableLiveData<List<Pray>>()
    val prays: LiveData<List<Pray>>
        get() = _prays

    private var _error = MutableLiveData<String>()
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