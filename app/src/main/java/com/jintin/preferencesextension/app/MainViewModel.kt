package com.jintin.preferencesextension.app

import android.app.Application
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager
import com.jintin.preferencesextension.liveData
import com.jintin.preferencesextension.rxjava3.observable
import io.reactivex.rxjava3.disposables.Disposable

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val preference = PreferenceManager.getDefaultSharedPreferences(application)
    private val disposable: Disposable

    val preferenceLiveData = preference.liveData<String>(MY_KEY)

    init {
        disposable = preference.observable<String>(MY_KEY).subscribe {
            println(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun setPreference(value: String) {
        preference.edit {
            putString(MY_KEY, value)
        }
    }

    companion object {
        const val MY_KEY = "MY_KEY"
    }
}