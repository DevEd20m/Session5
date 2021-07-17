package com.faztbit.restapilesson5.data.server.backend.config

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Bearer dsadasdasdasdad")
        return chain.proceed(requestBuilder.build())
    }
}