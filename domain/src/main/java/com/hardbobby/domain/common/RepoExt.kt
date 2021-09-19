package com.hardbobby.domain.common

fun <Response> SimpleResponse<Response>.mapToResult(): SimpleResult<Response> {
    return when {
        this.isSuccessful -> {
            val body = this.body()
            when {
                body?.data != null -> {
                    Result.Success.Data(body.data!!)
                }
                body?.message.equals("Success") -> {
                    Result.Success.NoData
                }
                else -> {
                    Result.Failure(SimpleError("Success but unknown failure"))
                }
            }
        }
        else -> Result.Failure(SimpleError(this.errorBody()?.string().orEmpty()))
    }
}