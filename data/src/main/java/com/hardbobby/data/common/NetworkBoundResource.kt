package com.hardbobby.data.common

import com.hardbobby.domain.common.Result
import com.hardbobby.domain.common.SimpleError
import com.hardbobby.domain.common.SimpleResult
import kotlinx.coroutines.flow.*


inline fun <ResultType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> SimpleResult<ResultType>,
    crossinline saveFetchResult: suspend (ResultType) -> Unit,
    crossinline isDataCacheAvailable: (ResultType) -> Boolean = { true }
): Flow<SimpleResult<ResultType>> = flow {
    val data = query().first()
    val flow = if (isDataCacheAvailable(data)) {
        try {
            emit(Result.Success.Data(data))
            var fetchData: ResultType? = null
            fetch().handleResult(
                successDataBlock = {
                    fetchData = it
                }
            )
            fetchData?.let { saveFetchResult(it) }
            query().map {
                Result.Success.Data(it)
            }
        } catch (throwable: Throwable) {
            query().map {
                Result.Failure(SimpleError(throwable.localizedMessage))
            }
        }
    } else {
        try {
            var fetchData: ResultType? = null
            fetch().handleResult(
                successDataBlock = {
                    fetchData = it
                    Result.Success.Data(it)
                }
            )
            fetchData?.let { saveFetchResult(it) }
            query().map {
                Result.Success.Data(it)
            }
        } catch (throwable: Throwable) {
            query().map {
                Result.Failure(SimpleError(throwable.localizedMessage))
            }
        }
    }

    emitAll(flow)
}

inline fun <ResultType> networkBoundResourceStream(
    crossinline queryList: () -> Flow<List<ResultType>>,
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> Flow<ResultType>,
    crossinline saveFetchResult: suspend (ResultType) -> Unit,
    crossinline isDataCacheAvailable: (ResultType) -> Boolean = { true }
): Flow<SimpleResult<ResultType>> = flow {
    val data = query().first()
    if (isDataCacheAvailable(data)) {
        queryList().first().map {
            emit(Result.Success.Data(it))
        }
        try {
            fetch().collect {
                saveFetchResult(it)
                emit(Result.Success.Data(it))
            }
        } catch (throwable: Throwable) {
            query().map {
                emit(Result.Failure(SimpleError(throwable.localizedMessage)))
            }
        }
    } else {
        try {
            fetch().collect {
                saveFetchResult(it)
                emit(Result.Success.Data(it))
            }
        } catch (throwable: Throwable) {
            query().map {
                emit(Result.Failure(SimpleError(throwable.localizedMessage)))
            }
        }
    }
}


