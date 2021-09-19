package com.hardbobby.cryptocurrencyapp.presentation.utils

import java.text.DecimalFormat


object NumberHelper {

    fun formatPriceChanges(value: Double): String {
        return DecimalFormat("##.##").format(value)
    }

    fun formatPrice(value: Double): String {
        return DecimalFormat("#,###").format(value)
    }

    fun String.addPrefix(): String {
        val value = this.toDoubleOrNull().orZero()
        return when {
            value < 0 -> {
                "-$value"
            }
            value > 0 -> {
                "+$value"
            }
            else -> this
        }
    }
}