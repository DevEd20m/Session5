package com.faztbit.restapilesson5.ui.Forecast

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.faztbit.restapilesson5.R
import com.faztbit.restapilesson5.data.server.backend.models.Forecast
import com.faztbit.restapilesson5.databinding.ItemForecastBinding
import com.faztbit.restapilesson5.ui.commons.basicDiffUtil
import com.faztbit.restapilesson5.ui.commons.inflate

class AdapterForecast() : RecyclerView.Adapter<AdapterForecast.ViewHolder>() {

    var list: List<Forecast> by basicDiffUtil(
        emptyList(), { old, new -> old.dt == new.dt }
    )

    //old
//    var list = emptyList<Forecast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_forecast, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
        }
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val binding = ItemForecastBinding.bind(view)

        fun bind(item: Forecast) {
            with(binding) {
                textViewDescriptionTimeNow.text = item.time
                textViewDescriptionWeatherNow.text = item.weatherData?.get(0)?.description
                textViewDescriptionPercentTemperature.text = item.mainData?.temp.toString()
                imageViewBackgroundStatusWeather.load("https://www.pngrepo.com/png/276664/512/cloudy-day-weather.png"){
                    crossfade(true)
                    crossfade(2)
                }
            }
        }
    }

}