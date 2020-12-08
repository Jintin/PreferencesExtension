package com.jintin.preferencesextension.app

import android.app.Application
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.jintin.preferencesextension.flow.flow
import com.jintin.preferencesextension.liveData
import com.jintin.preferencesextension.rxjava3.observable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val preference = PreferenceManager.getDefaultSharedPreferences(application)
    private val disposable: Disposable

    val preferenceLiveData = preference.liveData<String>(MY_KEY)

    init {
        disposable = triggerObservable()
        viewModelScope.launch {
            triggerFlow()
        }
    }

    private fun triggerObservable(): Disposable =
        preference.observable<String>(MY_KEY).subscribe {
            println("get update from observable : $it")
        }

    private suspend fun triggerFlow() {
        @Suppress("EXPERIMENTAL_API_USAGE")
        preference.flow<String>(MY_KEY).collect {
            println("get update from flow : $it")
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