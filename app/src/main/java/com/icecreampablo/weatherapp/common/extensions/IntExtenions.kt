package com.icecreampablo.weatherapp.common.extensions

import kotlin.math.roundToInt

fun Int.isLessThan(other: Int): Boolean {
    return this < other
}

fun Int.isGreaterThan(other: Int): Boolean {
    return this > other
}

fun Int.kmphToMps(): Int {
    val KMPH_TO_MPS = 3.6
    return (this / KMPH_TO_MPS).roundToInt()
}

fun Int.toBoolean(): Boolean = this == 1