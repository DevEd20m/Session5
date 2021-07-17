package com.faztbit.restapilesson5.data.server.backend.config

import com.faztbit.restapilesson5.data.server.backend.models.MainForecast
import com.faztbit.restapilesson5.data.server.backend.models.WeatherData
import retrofit2.Response
import retrofit2.http.*

interface EndPoint {
    @GET("forecast")
    suspend fun getCurrentForecast(
        @Query("q") country: String,
        @Query("appid") apiKey: String
    ): Response<MainForecast>

    @POST("login")
    suspend fun login(@Body data: WeatherData)

    @DELETE("movie/{movie_id}/rating")
//    @DELETE("movie/10/rating")
//    @DELETE("movie/hola/rating")
    //    @GET("ENDPOINTBASE/{movie_id}/forecast?q="VALORENVIADO EN Q"&appid="asjdlkasjdlkasfkasl")
    //    @GET("ENDPOINTBASE//forecast?q="VALORENVIADO EN Q"&appid="asjdlkasjdlkasfkasl")
    suspend fun removeMovie(@Path("movie_id") movieId: Int)
}