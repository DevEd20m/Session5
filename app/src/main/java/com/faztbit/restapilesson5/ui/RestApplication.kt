package com.faztbit.restapilesson5.ui

import android.app.Application
import com.faztbit.restapilesson5.data.device.SharedPreferencesRepository
import com.faztbit.restapilesson5.ui.commons.UserSingleton

class RestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        UserSingleton.initSingleton(SharedPreferencesRepository(this))
    }
}