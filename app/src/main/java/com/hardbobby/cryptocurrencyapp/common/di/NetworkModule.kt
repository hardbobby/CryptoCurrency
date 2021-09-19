package com.hardbobby.cryptocurrencyapp.common.di

import android.app.Application
import com.hardbobby.cryptocurrencyapp.common.service.FlowStreamAdapter
import com.hardbobby.cryptocurrencyapp.common.service.interceptor.ApiKeyInterceptor
import com.hardbobby.data.common.Keys
import com.hardbobby.data.remote.CryptoAPI
import com.hardbobby.data.remote.WebSocketApi
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.retry.ExponentialWithJitterBackoffStrategy
import com.tinder.scarlet.websocket.ShutdownReason
import com.tinder.scarlet.websocket.okhttp.OkHttpWebSocket
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofitBuilder(@Named("OkhttpRetrofit") okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Keys.baseUrl())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    @Named("OkhttpRetrofit")
    fun providesLoggingInterceptor(interceptor: Interceptor): OkHttpClient {
        val httpInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }

        return OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .addInterceptor(interceptor)
            .connectTimeout(25, TimeUnit.SECONDS)
            .callTimeout(25, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiKeyInterceptor(): Interceptor {
        return ApiKeyInterceptor(Keys.apiKey())
    }


    @Provides
    @Singleton
    @Named("OkhttpScarlet")
    fun provideScarletLoggingInterceptor(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(12, TimeUnit.SECONDS)
            .writeTimeout(12, TimeUnit.SECONDS)
            .callTimeout(12, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideScarlet(
        app: Application,
        @Named("OkhttpScarlet") httpClient: OkHttpClient,
    ): Scarlet = Scarlet(
        OkHttpWebSocket(
            httpClient,
            OkHttpWebSocket.SimpleRequestFactory(
                { Request.Builder().url(Keys.baseScarletUrl()).build() },
                { ShutdownReason.GRACEFUL }
            )),
        Scarlet.Configuration(
            backoffStrategy = ExponentialWithJitterBackoffStrategy(5000, 5000),
            messageAdapterFactories = listOf(GsonMessageAdapter.Factory()),
            streamAdapterFactories = listOf(FlowStreamAdapter.Factory),
            lifecycle = AndroidLifecycle.ofApplicationForeground(app)
        )
    )


    @Singleton
    @Provides
    fun providesAPI(retrofit: Retrofit.Builder): CryptoAPI {
        return retrofit.build().create(CryptoAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesWebSocketAPI(scarlet: Scarlet): WebSocketApi {
        return scarlet.create(WebSocketApi::class.java)
    }

}