package com.hardbobby.domain.common.usecase

import com.hardbobby.domain.common.SimpleResult
import kotlinx.coroutines.flow.Flow

abstract class BaseParamUseCase <in Param,T> {
    abstract suspend fun execute(param: Param): Flow<SimpleResult<T>>
}