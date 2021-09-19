package com.hardbobby.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hardbobby.domain.dto.CoinInfoResponse
import com.hardbobby.domain.dto.CryptoResponse
import com.hardbobby.domain.dto.RawCoinDetailResponse
import com.hardbobby.domain.dto.RawCoinResponse


@Entity(tableName = "Crypto")
data class Crypto(
    @PrimaryKey
    val idCrypto: String = "",
    val fullName: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val changeHour: Double = 0.0,
    val changePCTHour: Double = 0.0,
    val pageNo: Int = 0
)

fun Crypto.toCryptoResponse(): CryptoResponse {
    return CryptoResponse(
        CoinInfoResponse(fullName, idCrypto, name),
        RawCoinResponse(
            RawCoinDetailResponse(
                changeHour, changePCTHour, price
            )
        )
    )

}
