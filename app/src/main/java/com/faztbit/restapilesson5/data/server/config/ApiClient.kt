package com.faztbit.restapilesson5.data.server.config

import com.faztbit.restapilesson5.ui.commons.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private fun addInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        return interceptor
    }


    private val interceptor = AuthInterceptor()
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(addInterceptor())
        .addInterceptor(interceptor)
        .connectTimeout(Constants.connectTimeout, TimeUnit.MINUTES)
        .readTimeout(Constants.readTimeOut, TimeUnit.SECONDS)
        .writeTimeout(Constants.writeTimeout, TimeUnit.SECONDS)
        .build()


    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build()
    }

    val endPoint: EndPoint = retrofit().create(EndPoint::class.java)
}