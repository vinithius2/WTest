package com.vinithius.wtest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vinithius.wtest.datasource.models.CodigoPostalData
import com.vinithius.wtest.datasource.models.CodigoPostalEntity
import com.vinithius.wtest.datasource.models.LoadingData
import com.vinithius.wtest.datasource.repository.WTestRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope


class WTestViewModel(private val repository: WTestRepository) : ViewModel() {

//    private val _codigoPostal = MutableLiveData<List<CodigoPostalData>?>()
//    val codigoPostal: LiveData<List<CodigoPostalData>?>
//        get() = _codigoPostal
//
//    private val _codigoPostalStatusLoading = MutableLiveData<LoadingData>()
//    val codigoPostalStatusLoading: LiveData<LoadingData>
//        get() = _codigoPostalStatusLoading

//    fun getCodigoPostal() {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val codigoPostal = repository.getCodigoPostalList(::getStatusLoading)
//                _codigoPostal.postValue(codigoPostal)
//            } catch (e: Exception) {
//                Log.e("getCodigoPostal", e.toString())
//            }
//        }
//    }

    var currentResult: LiveData<PagingData<CodigoPostalEntity>>? = null

    /**
     * Get character list using Paging3.
     */
    fun getCodigoPostal(search: String? = null): LiveData<PagingData<CodigoPostalEntity>>? {
        try {
            currentResult = repository.getCodigoPostalList(search).cachedIn(viewModelScope)
        } catch (e: Exception) {
            Log.e("Error list getCodigoPostal", e.toString())
        }
        return currentResult
    }

//    private fun getStatusLoading(loadingData: LoadingData) {
//        _codigoPostalStatusLoading.postValue(loadingData)
//    }

}