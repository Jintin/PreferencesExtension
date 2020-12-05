package com.jintin.preferencesextension

import android.content.SharedPreferences

inline fun <reified T> SharedPreferences.getValue(key: String): T {
    val value: Any? = when (T::class) {
        Boolean::class -> getBoolean(key, false)
        Int::class -> getInt(key, 0)
        Long::class -> getLong(key, 0)
        Float::class -> getFloat(key, 0f)
        String::class -> getString(key, "")
        Set::class -> getStringSet(key, emptySet())
        else -> throw RuntimeException("Not support type: ${T::class} for SharedPreferences.liveData")
    }
    return value as T
}

inline fun <reified T> SharedPreferences.liveData(key: String) =
    object : PreferenceLiveData<T>(this, key) {
        override fun getPreferencesValue(): T = getValue(key)
    }