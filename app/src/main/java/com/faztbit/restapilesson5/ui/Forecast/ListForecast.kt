package com.faztbit.restapilesson5.ui.Forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.faztbit.restapilesson5.data.server.backend.ServerRepository
import com.faztbit.restapilesson5.data.server.backend.config.ApiClient
import com.faztbit.restapilesson5.databinding.ActivityListForecastBinding
import com.faztbit.restapilesson5.ui.commons.UserSingleton
import com.faztbit.restapilesson5.ui.commons.getViewModel
import com.faztbit.restapilesson5.ui.commons.toast
import kotlinx.coroutines.Dispatchers

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
        events()
    }

    override fun onStart() {
        super.onStart()
        if (UserSingleton.getSession()) {
            binding.textViewSessionName.text = UserSingleton.getName()
        } else {
            binding.textViewSessionName.text = "SIN SESSIÓN"
        }
    }

    private fun events() {
        with(binding) {
            floatingButton.setOnClickListener {
                UserSingleton.setSession(true)
                UserSingleton.setName("Edmundo")
            }
        }
    }

    fun setUpViewModel() {
        viewModel = getViewModel {
            ForecastViewModel(
                ServerRepository(
                    ApiClient.endPoint,
                    "b4105763183d0d9a84906913ea4d972d", application
                ), Dispatchers.Main
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

        })
        viewModel.onMessageError.observe(this, {
            toast(it)
        })
    }

}