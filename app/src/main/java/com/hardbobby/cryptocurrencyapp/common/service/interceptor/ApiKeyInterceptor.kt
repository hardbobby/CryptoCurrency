package com.hardbobby.cryptocurrencyapp.common.service.interceptor

import com.hardbobby.data.common.Constants.QUERY_PARAM_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url =
            chain.request().url.newBuilder().addQueryParameter(QUERY_PARAM_API_KEY, apiKey).build()
        return chain.proceed(
            chain.request()
                .newBuilder()
                .url(url)
                .build()
        )
    }
}