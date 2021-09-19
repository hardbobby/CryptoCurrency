package com.hardbobby.data.remote

import com.hardbobby.data.common.Constants.QUERY_PARAM_LIMIT
import com.hardbobby.data.common.Constants.QUERY_PARAM_PAGE
import com.hardbobby.data.common.Constants.QUERY_PARAM_TSYM
import com.hardbobby.data.common.SimpleResponse
import com.hardbobby.domain.dto.CryptoResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoAPI {

    @GET("data/top/totaltoptiervolfull")
    suspend fun getCryptoList(
        @Query(QUERY_PARAM_LIMIT) limit: Int,
        @Query(QUERY_PARAM_PAGE) pageNum: Int,
        @Query(QUERY_PARAM_TSYM) tsym: String
    ) : SimpleResponse<List<CryptoResponse>>
}