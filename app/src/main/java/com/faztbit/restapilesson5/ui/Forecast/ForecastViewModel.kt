package com.faztbit.restapilesson5.ui.Forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deved.weatherapp.coroutines.ScopeViewModel
import com.faztbit.restapilesson5.coroutines.ScopeViewModelTwo
import com.faztbit.restapilesson5.data.server.backend.ServerRepository
import com.faztbit.restapilesson5.data.server.backend.dataReponse.DataResponse
import com.faztbit.restapilesson5.data.server.backend.models.Forecast
import com.faztbit.restapilesson5.data.server.backend.models.MainForecast
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ForecastViewModel(
    private val repository: ServerRepository,
    dispatcher: CoroutineDispatcher,
) : ScopeViewModel(dispatcher) {
    private var _forecast = MutableLiveData<List<Forecast>>()
    val forecast: LiveData<List<Forecast>> get() = _forecast
    private var _onMessageError = MutableLiveData<String>()
    val onMessageError: LiveData<String> get() = _onMessageError

    init {
        launch {
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