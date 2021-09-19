package com.hardbobby.domain.dto

data class CryptoModelView(
    var id: String = "",
    var name: String = "",
    var fullName: String = "",
    var currentPrice: Double = 0.0,
    var changePrice: Double = 0.0,
    var changePricePercent: Double = 0.0
)

fun CryptoResponse.toCryptoModelView(): CryptoModelView {
    return if (coinInfo != null && raw?.rawCoinDetail != null) {
        CryptoModelView(
            coinInfo.id,
            coinInfo.name,
            coinInfo.fullName,
            raw.rawCoinDetail.price,
            raw.rawCoinDetail.changeHour,
            raw.rawCoinDetail.changePCTHour
        )
    } else {
        CryptoModelView()
    }
}
