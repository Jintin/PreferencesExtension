package com.jintin.preferencesextension

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

abstract class PreferenceLiveData<T>(
    private val preferences: SharedPreferences,
    private val key: String,
    private val defValue: T,
    private val notifyInitValue: Boolean
) : LiveData<T>(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onActive() {
        super.onActive()
        preferences.registerOnSharedPreferenceChangeListener(this)
        updateValue(getPreferencesValue())
    }

    override fun onInactive() {
        super.onInactive()
        preferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == this.key) {
            updateValue(getPreferencesValue())
        }
    }

    private fun updateValue(newValue: T) {
        if (!notifyInitValue && value == null && newValue == defValue) {
            return
        }
        if (value != newValue) {
            value = newValue
        }
    }

    abstract fun getPreferencesValue(): T
}