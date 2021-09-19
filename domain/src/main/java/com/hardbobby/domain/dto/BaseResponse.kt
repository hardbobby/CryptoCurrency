package com.hardbobby.domain.dto

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @field:SerializedName("Type")
    var type: Int? = 0,
    @field:SerializedName("Data")
    var data: T? = null,
    @field:SerializedName("Message")
    var message: String? = "",
)