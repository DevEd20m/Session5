package com.faztbit.restapilesson5.data.server.models

import com.google.gson.annotations.SerializedName

data class MainForecast(@SerializedName("list") val list: List<Forecast>)
data class Forecast(
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("main")
    val mainData: MainData?,
    @SerializedName("weather")
    val weatherData: List<WeatherData>?,
    @SerializedName("dt_txt")
    val time: String?
)

data class MainData(
    @SerializedName("temp")
    val temp: Double?,
    @SerializedName("temp_min")
    val tempMin: Double?,
    @SerializedName("temp_max")
    val tempMax: Double?
)

data class WeatherData(
    @SerializedName("id")
    val id: Double?,
    @SerializedName("main")
    val main: String?,
    @SerializedName("description")
    val description: String?
)
