package com.hardbobby.domain.common

import com.hardbobby.domain.dto.BaseResponse
import retrofit2.Response

typealias SimpleResult<T> = Result<T, SimpleError>

typealias SimpleResponse<T> = Response<BaseResponse<T>>
