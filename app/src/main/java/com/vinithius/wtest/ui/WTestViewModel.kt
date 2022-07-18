package com.vinithius.wtest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vinithius.wtest.datasource.models.CodigoPostalEntity
import com.vinithius.wtest.datasource.models.LoadingData
import com.vinithius.wtest.datasource.repository.WTestRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WTestViewModel(private val repository: WTestRepository) : ViewModel() {

    private val _codigoPostalStatusLoading = MutableLiveData<LoadingData>()
    val codigoPostalStatusLoading: LiveData<LoadingData>
        get() = _codigoPostalStatusLoading

    private val _isExistCodigoPostal = MutableLiveData<Boolean>()
    val isExistCodigoPostal: LiveData<Boolean>
        get() = _isExistCodigoPostal

    private val _finishDownloadAndSave = MutableLiveData<Boolean>()
    val finishDownloadAndSave: LiveData<Boolean>
        get() = _finishDownloadAndSave

    var currentResult: LiveData<PagingData<CodigoPostalEntity>>? = null

    private fun getStatusLoading(loadingData: LoadingData) {
        _codigoPostalStatusLoading.postValue(loadingData)
    }

    fun getIsExistCodigoPostal() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val exist = repository.getExistCodigoPostal()
                _isExistCodigoPostal.postValue(exist)
            } catch (e: Exception) {
                Log.e("getCodigoPostal", e.toString())
            }
        }
    }

    /**
     * Download codigo_postal and save in database.
     */
    fun getDownloadAndSaveCSV() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _finishDownloadAndSave.postValue(false)
                val exist = repository.getDownloadAndSaveCSV(::getStatusLoading)
                _finishDownloadAndSave.postValue(exist)
            } catch (e: Exception) {
                Log.e("getCodigoPostal", e.toString())
            }
        }
    }

    /**
     * Get codigo_postal list using Paging3.
     */
    fun getCodigoPostal(query: String? = null): LiveData<PagingData<CodigoPostalEntity>>? {
        try {
            currentResult = repository.getPagerCodigoPostalList(query).cachedIn(viewModelScope)
        } catch (e: Exception) {
            Log.e("Error list getCodigoPostal", e.toString())
        }
        return currentResult
    }

}