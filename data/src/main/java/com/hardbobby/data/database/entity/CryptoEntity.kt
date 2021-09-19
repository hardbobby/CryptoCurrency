package com.hardbobby.data.database.entity

import androidx.room.Entity

@Entity
data class CryptoEntity(
    var id: String,
    var name: String,
    var fullName: String,
    var currentPrice: Double,
    var changePrice: Double,
    var changePricePercent: Double
)
