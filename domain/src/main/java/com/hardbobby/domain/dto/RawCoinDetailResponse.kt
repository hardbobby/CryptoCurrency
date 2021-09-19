package com.hardbobby.domain.dto

import com.google.gson.annotations.SerializedName

data class RawCoinDetailResponse(
    @field:SerializedName("CHANGEHOUR")
    val changeHour: Double,
    @field:SerializedName("CHANGEPCTHOUR")
    val changePCTHour: Double,
    @field:SerializedName("PRICE")
    val price: Double
)