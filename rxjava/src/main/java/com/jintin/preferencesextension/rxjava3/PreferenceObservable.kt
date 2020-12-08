package com.jintin.preferencesextension.rxjava3

import android.content.SharedPreferences
import com.jintin.preferencesextension.get
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

inline fun <reified T> SharedPreferences.observable(
    key: String,
    notifyInitValue: Boolean = true
): Observable<T> {
    return object : PreferenceObservable<T>(this, key, get(key), notifyInitValue) {
        override fun getPreferencesValue(): T = get(key)
    }
}

abstract class PreferenceObservable<T>(
    private val preferences: SharedPreferences,
    private val key: String,
    private val defValue: T,
    private val notifyInitValue: Boolean
) : Observable<T>() {

    override fun subscribeActual(observer: Observer<in T>) {
        val disposable = PreferenceDisposable(observer)
        observer.onSubscribe(disposable)
    }

    abstract fun getPreferencesValue(): T

    private inner class PreferenceDisposable(
        private val observer: Observer<in T>
    ) : Disposable, SharedPreferences.OnSharedPreferenceChangeListener {

        private var value: T? = null
        private var isDispose = false

        init {
            updateValue(getPreferencesValue())
            preferences.registerOnSharedPreferenceChangeListener(this)
        }

        override fun onSharedPreferenceChanged(
            sharedPreferences: SharedPreferences?,
            key: String?
        ) {
            if (key == this@PreferenceObservable.key) {
                updateValue(getPreferencesValue())
            }
        }

        override fun dispose() {
            isDispose = true
            observer.onComplete()
            preferences.unregisterOnSharedPreferenceChangeListener(this)
        }

        override fun isDisposed() = isDispose

        private fun updateValue(newValue: T) {
            if (!notifyInitValue && value == null && newValue == defValue) {
                return
            }
            if (value != newValue) {
                value = newValue
                observer.onNext(value)
            }
        }
    }
}