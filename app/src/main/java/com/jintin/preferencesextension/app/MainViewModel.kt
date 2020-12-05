package com.jintin.preferencesextension.app

import android.app.Application
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager
import com.jintin.preferencesextension.liveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val preference = PreferenceManager.getDefaultSharedPreferences(application)

    val preferenceLiveData = preference.liveData<String>(MY_KEY)

    fun setPreference(value: String) {
        preference.edit {
            putString(MY_KEY, value)
        }
    }

    companion object {
        const val MY_KEY = "MY_KEY"
    }
}