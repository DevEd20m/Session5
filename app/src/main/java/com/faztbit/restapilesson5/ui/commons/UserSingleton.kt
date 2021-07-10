package com.faztbit.restapilesson5.ui.commons

import com.faztbit.restapilesson5.data.device.SharedPreferencesRepository


object UserSingleton {
    private var repository: SharedPreferencesRepository? = null
    fun initSingleton(repository: SharedPreferencesRepository) {
        this.repository = repository
    }

    fun setSession(value: Boolean) = repository?.putBoolean(Constants.SESSION, value)
    fun getSession(): Boolean = repository?.getBoolean(Constants.SESSION, false)!!
    fun setName(value: String) = repository?.putString(Constants.NAME_USER, value)
    fun getName(): String = repository?.getString(Constants.NAME_USER, "").toString()
}