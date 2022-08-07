package com.icecreampablo.weatherapp.common.extensions

import kotlin.math.roundToInt

fun Double.toPrecipitationDisplayValue(): String {
    return when {
        this == 0.0 -> ""
        this < 1 -> "<1 mm"
        else -> "${this.roundToInt()} mm"
    }
}

fun Double.kmphToMps(): Double {
    val KMPH_TO_MPS = 3.6
    return (this / KMPH_TO_MPS)
}