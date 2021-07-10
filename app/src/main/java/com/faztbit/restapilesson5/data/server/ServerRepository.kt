package com.faztbit.restapilesson5.data.server

import com.faztbit.restapilesson5.data.server.config.EndPoint
import com.faztbit.restapilesson5.data.server.dataReponse.DataResponse
import com.faztbit.restapilesson5.data.server.dataReponse.ErrorServer
import com.faztbit.restapilesson5.data.server.models.Forecast
import com.faztbit.restapilesson5.data.server.models.MainForecast
import com.google.gson.Gson
import java.util.concurrent.TimeoutException


class ServerRepository(private val endPoint: EndPoint, private val apiKey: String) {

    suspend fun getCurrentForecast(country: String): DataResponse<MainForecast> {
        try {

            val result = endPoint.getCurrentForecast(country, apiKey)
            if (result.isSuccessful) {
                result.body()?.let {
                    return DataResponse.Success(it)
                }
            }

            return DataResponse.ServerError(
                Gson().fromJson(
                    result.errorBody()?.charStream(),
                    ErrorServer::class.java
                )
            )
        } catch (e: TimeoutException) {
            return DataResponse.TimeOutException(e)
        } catch (e: Exception) {
            return DataResponse.ExceptionError(e)
        }
    }
}