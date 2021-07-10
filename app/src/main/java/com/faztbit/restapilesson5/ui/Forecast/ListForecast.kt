package com.faztbit.restapilesson5.ui.Forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.faztbit.restapilesson5.data.server.ServerRepository
import com.faztbit.restapilesson5.data.server.config.ApiClient
import com.faztbit.restapilesson5.databinding.ActivityListForecastBinding
import com.faztbit.restapilesson5.ui.commons.getViewModel
import com.faztbit.restapilesson5.ui.commons.toast

class ListForecast : AppCompatActivity() {
    private lateinit var binding: ActivityListForecastBinding
    private lateinit var adapterForecast: AdapterForecast
    private lateinit var viewModel: ForecastViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
        setUpRecyclerView()
        setUpViewModelObserver()
    }

    private fun setUpViewModel() {
        viewModel = getViewModel {
            ForecastViewModel(
                ServerRepository(
                    ApiClient.endPoint,
                    "b4105763183d0d9a84906913ea4d972d"
                )
            )
        }
    }

    private fun setUpRecyclerView() {
        with(binding) {
            adapterForecast = AdapterForecast()
            recyclerViewContent.layoutManager = LinearLayoutManager(this@ListForecast)
            recyclerViewContent.adapter = adapterForecast
        }
    }

    private fun setUpViewModelObserver() {
        viewModel.forecast.observe(this, {
            adapterForecast.list = it
//            adapterForecast.notifyDataSetChanged()
        })
        viewModel.onMessageError.observe(this, {
            toast(it)
        })
    }

}