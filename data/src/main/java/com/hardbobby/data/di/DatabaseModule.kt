package com.hardbobby.data.di

import android.content.Context
import androidx.room.Room
import com.hardbobby.data.common.Constants
import com.hardbobby.data.database.CryptoDatabase
import com.hardbobby.data.database.dao.CryptoDao
import com.hardbobby.data.database.dao.WebSocketDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CryptoDatabase {
        return Room.databaseBuilder(
            appContext, CryptoDatabase::class.java,
            Constants.DatabaseName
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideChannelDao(cryptoDatabase: CryptoDatabase): CryptoDao {
        return cryptoDatabase.cryptoDao()
    }

    @Provides
    @Singleton
    fun provideWebSocketDao(cryptoDatabase: CryptoDatabase): WebSocketDao {
        return cryptoDatabase.webSocketDao()
    }


}