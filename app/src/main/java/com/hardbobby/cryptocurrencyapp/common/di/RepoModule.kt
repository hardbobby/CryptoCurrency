package com.hardbobby.cryptocurrencyapp.common.di

import com.hardbobby.data.repositoryimpl.CryptoRepositoryImpl
import com.hardbobby.domain.repository.CryptoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun providesCryptoRepository(cryptoRepository: CryptoRepositoryImpl): CryptoRepository {
        return cryptoRepository
    }
}