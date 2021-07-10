package com.faztbit.restapilesson5.data.device

import android.app.Application
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.faztbit.restapilesson5.ui.commons.get
import com.faztbit.restapilesson5.ui.commons.put

class SharedPreferencesRepository(private val application: Application) {

    private val sharedPreferencesEncrypted by lazy {
        EncryptedSharedPreferences.create(
            "Lesson5Encrypted",
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            application,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun getBoolean(key: String, default: Boolean): Boolean {
        return sharedPreferencesEncrypted.get(key, default)
    }

    fun putBoolean(key: String, value: Boolean?) {
        sharedPreferencesEncrypted.put(key, value)
    }

    fun getString(key: String, default: String): String {
        return sharedPreferencesEncrypted.get(key, default)
    }

    fun putString(key: String, value: String?) {
        sharedPreferencesEncrypted.put(key, value)
    }
}