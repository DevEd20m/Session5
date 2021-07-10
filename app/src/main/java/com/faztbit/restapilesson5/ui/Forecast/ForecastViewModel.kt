package com.faztbit.restapilesson5.ui.Forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faztbit.restapilesson5.data.server.ServerRepository
import com.faztbit.restapilesson5.data.server.dataReponse.DataResponse
import com.faztbit.restapilesson5.data.server.models.Forecast
import com.faztbit.restapilesson5.data.server.models.MainForecast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ForecastViewModel(private val repository: ServerRepository) : ViewModel() {
    private var _forecast = MutableLiveData<List<Forecast>>()
    val forecast: LiveData<List<Forecast>> get() = _forecast
    private var _onMessageError = MutableLiveData<String>()
    val onMessageError: LiveData<String> get() = _onMessageError

    init {
        GlobalScope.launch {
            val result = repository.getCurrentForecast("Lima")
            validateResult(result)
        }
    }

    private fun validateResult(result: DataResponse<MainForecast>) {
        when (result) {
            is DataResponse.ExceptionError -> _onMessageError.postValue(result.errorCode.message)
            is DataResponse.ServerError -> _onMessageError.postValue(result.errorCode.message)
            is DataResponse.Success -> _forecast.postValue(result.data?.list)
            is DataResponse.TimeOutException -> _onMessageError.postValue(result.errorCode.message)
            is DataResponse.NotConnectivity -> _onMessageError.postValue(result.data)
        }
    }


}