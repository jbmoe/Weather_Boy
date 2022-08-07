package com.icecreampablo.weatherapp.common.helpers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonHelpers {
    inline fun <reified T> encode(data: T?): String {
        return Gson().toJson(data)
    }

    inline fun <reified T> decode(data: String?): T? {
        if (data == null) {
            return null
        }

        val itemType = object : TypeToken<T>() {}.type
        return Gson().fromJson(data, itemType)
    }
}