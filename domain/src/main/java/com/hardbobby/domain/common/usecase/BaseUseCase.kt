package com.hardbobby.domain.common.usecase

import com.hardbobby.domain.common.SimpleResult
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<T> {
    abstract suspend fun execute(): Flow<SimpleResult<T>>
}