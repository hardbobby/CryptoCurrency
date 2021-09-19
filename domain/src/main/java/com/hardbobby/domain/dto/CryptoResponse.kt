package com.hardbobby.domain.dto

import com.google.gson.annotations.SerializedName

data class CryptoResponse(
    @field:SerializedName("CoinInfo")
    val coinInfo: CoinInfoResponse?,
    @field:SerializedName("RAW")
    val raw: RawCoinResponse?
)

