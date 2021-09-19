package com.hardbobby.data.mapper

import com.hardbobby.data.database.entity.Crypto
import com.hardbobby.data.database.entity.toCryptoResponse
import com.hardbobby.domain.dto.CryptoResponse

class CryptoDaoMapper : Mapper<List<CryptoResponse>, List<Crypto>> {
    override fun mapFromResponse(response: List<CryptoResponse>?, pageNum: Int): List<Crypto> {
        return response?.map {
            Crypto(
                it.coinInfo?.id.orEmpty(),
                it.coinInfo?.name.orEmpty(),
                it.coinInfo?.fullName.orEmpty(),
                it.raw?.rawCoinDetail?.price ?: 0.0,
                it.raw?.rawCoinDetail?.changeHour ?: 0.0,
                it.raw?.rawCoinDetail?.changePCTHour ?: 0.0,
                pageNo = pageNum
            )
        }.orEmpty()
    }

    override fun mapFromDao(model: List<Crypto>?): List<CryptoResponse> {
        return model?.map {
            it.toCryptoResponse()
        }.orEmpty()
    }
}