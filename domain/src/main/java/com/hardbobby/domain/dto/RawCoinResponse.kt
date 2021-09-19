package com.hardbobby.domain.dto

import com.google.gson.annotations.SerializedName

data class RawCoinResponse(
    @field:SerializedName("IDR")
    val rawCoinDetail: RawCoinDetailResponse
)