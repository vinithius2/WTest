package com.vinithius.wtest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinithius.wtest.datasource.models.CodigoPostalResponse
import com.vinithius.wtest.datasource.repository.WTestRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WTestViewModel(private val repository: WTestRepository) : ViewModel() {

    private val _codigoPostal = MutableLiveData<List<CodigoPostalResponse>?>()
    val codigoPostal: LiveData<List<CodigoPostalResponse>?>
        get() = _codigoPostal

    private val _codigoPostalLoading = MutableLiveData<Boolean>()
    val codigoPostalLoading: LiveData<Boolean>
        get() = _codigoPostalLoading

    private val _codigoPostalError = MutableLiveData<Boolean>()
    val codigoPostalError: LiveData<Boolean>
        get() = _codigoPostalError

    private val _codigoPostalStatus = MutableLiveData<String>()
    val codigoPostalStatus: LiveData<String>
        get() = _codigoPostalStatus

    private val _codigoPostalCount = MutableLiveData<Int>()
    val codigoPostalCount: LiveData<Int>
        get() = _codigoPostalCount

    private val _codigoPostalSize = MutableLiveData<Int>()
    val codigoPostalSize: LiveData<Int>
        get() = _codigoPostalSize

    private val _codigoPostalIsProgress = MutableLiveData<Boolean>()
    val codigoPostalIsProgress: LiveData<Boolean>
        get() = _codigoPostalIsProgress

    fun getCodigoPostal() {
        CoroutineScope(Dispatchers.IO).launch {
            _codigoPostalError.postValue(false)
            try {
                _codigoPostalLoading.postValue(true)
                val codigoPostal = repository.getCodigoPostalList(::getStatusLoading)
                _codigoPostal.postValue(codigoPostal)
            } catch (e: Exception) {
                _codigoPostalError.postValue(true)
                Log.e("getCodigoPostal", e.toString())
            }
            _codigoPostalLoading.postValue(false)
        }
    }

    private fun getStatusLoading(isProgress : Boolean, status: String, count: Int? = null, size: Int? = null) {
        _codigoPostalIsProgress.postValue(isProgress)
        size?.let { _codigoPostalSize.postValue(it) }
        count?.let { _codigoPostalCount.postValue(it) }
        _codigoPostalStatus.postValue(status)
    }

}