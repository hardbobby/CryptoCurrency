package com.hardbobby.data.common

import com.hardbobby.domain.dto.SocketSubscription

object Constants {
    const val QUERY_PARAM_API_KEY = "api_key"
    const val QUERY_PARAM_LIMIT = "limit"
    const val QUERY_PARAM_PAGE = "page"
    const val QUERY_PARAM_TSYM = "tsym"

    const val DatabaseName = "CryptoDatabase"

    val WEB_SOCKET_SUBSCRIBE_MODEL = SocketSubscription("SubAdd", listOf("11~DOGE", "11~ETH"))

    val DOGE_LABEL =  "DOGE"
    val ETH_LABEL =  "ETH"



}