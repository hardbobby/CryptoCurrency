package com.hardbobby.data.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.hardbobby.data.database.entity.CoinInfo
import com.hardbobby.domain.dto.CoinInfoResponse

class Converter {

    @TypeConverter
    fun fromCoinInfoToCoinInfoResponse(coinInfo: CoinInfo) : CoinInfoResponse {
        return CoinInfoResponse(coinInfo.fullName,coinInfo.id,coinInfo.name)
    }

    @TypeConverter
    fun fromCoinInfoResponseToCoinInfo(coinInfoResponse: CoinInfoResponse) : CoinInfo {
        return CoinInfo(coinInfoResponse.fullName,coinInfoResponse.id,coinInfoResponse.name)
    }


}