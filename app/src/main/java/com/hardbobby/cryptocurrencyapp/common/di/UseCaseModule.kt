package com.hardbobby.cryptocurrencyapp.common.di

import com.hardbobby.data.repositoryimpl.CryptoRepositoryImpl
import com.hardbobby.domain.usecase.CryptoUseCase
import com.hardbobby.domain.usecase.WebSocketUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesCryptoUseCase(repository: CryptoRepositoryImpl): CryptoUseCase {
        return CryptoUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesWebSocketUseCase(repository: CryptoRepositoryImpl): WebSocketUseCase {
        return WebSocketUseCase(repository)
    }
}