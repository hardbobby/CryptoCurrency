package com.hardbobby.domain.usecase

import com.hardbobby.domain.common.Result
import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.common.usecase.BaseParamUseCase
import com.hardbobby.domain.dto.CryptoModelView
import com.hardbobby.domain.dto.CryptoRequest
import com.hardbobby.domain.dto.toCryptoModelView
import com.hardbobby.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CryptoUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) : BaseParamUseCase<CryptoRequest, List<CryptoModelView>>() {

    override suspend fun execute(param: CryptoRequest): Flow<SimpleResult<List<CryptoModelView>>> {
        return cryptoRepository.getCryptoData(param).map { data ->
            data.mapData(
                map = { cryptoResponse ->
                    Result.Success.Data(
                        cryptoResponse.map {
                            it.toCryptoModelView()
                        }
                    )
                }
            )
        }
    }
}