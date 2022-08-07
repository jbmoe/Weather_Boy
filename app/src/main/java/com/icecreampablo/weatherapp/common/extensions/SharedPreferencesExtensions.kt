package com.icecreampablo.weatherapp.common.extensions

import android.content.SharedPreferences

fun SharedPreferences.getString(label: String, defaultValue: String = ""): String? {
    return this.getString(label, defaultValue)
}