package com.hardbobby.domain.common

data class SimpleError(
    var errorMessage: String = ""
) : Error()