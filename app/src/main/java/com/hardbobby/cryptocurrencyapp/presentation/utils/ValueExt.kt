package com.hardbobby.cryptocurrencyapp.presentation.utils

fun Boolean?.orFalse() = this ?: false

fun Boolean?.orTrue() = this ?: true

fun Long?.orZero() = this ?: 0L

fun Double?.orZero() = this ?: 0.0

fun Int?.orZero() = this ?: 0