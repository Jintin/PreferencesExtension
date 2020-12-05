package com.jintin.preferencesextension

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

abstract class PreferenceLiveData<T>(
    private val preferences: SharedPreferences,
    private val target: String
) : LiveData<T>(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onActive() {
        super.onActive()
        preferences.registerOnSharedPreferenceChangeListener(this)
        changeValue(getPreferencesValue())
    }

    override fun onInactive() {
        super.onInactive()
        preferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == target) {
            changeValue(getPreferencesValue())
        }
    }

    private fun changeValue(newValue: T) {
        if (value != newValue) {
            value = newValue
        }
    }

    abstract fun getPreferencesValue(): T
}