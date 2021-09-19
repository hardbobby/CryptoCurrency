package com.hardbobby.data.di

import com.hardbobby.data.mapper.CryptoDaoMapper
import com.hardbobby.data.mapper.WebSocketDaoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    @Singleton
    fun providesCryptoDaoMapper(): CryptoDaoMapper {
        return CryptoDaoMapper()
    }

    @Provides
    @Singleton
    fun providesWebSocketDaoMapper(): WebSocketDaoMapper {
        return WebSocketDaoMapper()
    }
}