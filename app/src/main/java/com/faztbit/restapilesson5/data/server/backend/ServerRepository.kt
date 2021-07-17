package com.faztbit.restapilesson5.data.server.backend

import android.app.Application
import com.faztbit.restapilesson5.data.server.backend.config.EndPoint
import com.faztbit.restapilesson5.data.server.backend.dataReponse.DataResponse
import com.faztbit.restapilesson5.data.server.backend.dataReponse.ErrorServer
import com.faztbit.restapilesson5.data.server.backend.models.MainForecast
import com.faztbit.restapilesson5.ui.commons.isAvailableNetwork
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeoutException


class ServerRepository(
    private val endPoint: EndPoint,
    private val apiKey: String,
    private val app: Application
) {

    suspend fun getCurrentForecast(country: String): DataResponse<MainForecast> {
        withContext(Dispatchers.IO) {
            try {
                if (!app.isAvailableNetwork()) {
                    return@withContext DataResponse.NotConnectivity("No tienes conexión a internet")
                }
                val result = endPoint.getCurrentForecast(country, apiKey)
                if (result.isSuccessful) {
                    result.body()?.let {
                        return@withContext DataResponse.Success(it)
                    }
                }


                return@withContext DataResponse.ServerError(
                    Gson().fromJson(
                        result.errorBody()?.charStream(),
                        ErrorServer::class.java
                    )
                )
            } catch (e: TimeoutException) {
                return@withContext DataResponse.TimeOutException(e)
            } catch (e: Exception) {
                return@withContext DataResponse.ExceptionError(e)
            }
        }
    }
}