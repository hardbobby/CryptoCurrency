package com.hardbobby.domain.dto

import com.google.gson.annotations.SerializedName

data class SocketSubscription(
    @field:SerializedName("action")
    var action: String,
    @field:SerializedName("subs")
    var subs: List<String>
)
