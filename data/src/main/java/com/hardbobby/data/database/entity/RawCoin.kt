package com.hardbobby.data.database.entity

import androidx.room.Entity


@Entity(tableName = "RawCoin")
data class RawCoin(
    val changeHour: Double,
    val changePCTHour: Double,
    val price: Double
)
