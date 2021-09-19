package com.hardbobby.domain.dto

import com.google.gson.annotations.SerializedName

data class CoinInfoResponse(
    @field:SerializedName("FullName")
    val fullName: String,
    @field:SerializedName("Id")
    val id: String,
    @field:SerializedName("Name")
    val name: String
)