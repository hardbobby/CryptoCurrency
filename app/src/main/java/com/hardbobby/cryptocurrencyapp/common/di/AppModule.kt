package com.hardbobby.cryptocurrencyapp.common.di

import android.content.Context
import android.content.SharedPreferences
import com.hardbobby.cryptocurrencyapp.presentation.utils.Constant
import com.hardbobby.cryptocurrencyapp.presentation.utils.SharePreferencesManager
import com.hardbobby.cryptocurrencyapp.presentation.utils.UserManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesSharePreferences(sharedPreferences: SharedPreferences): SharePreferencesManager {
        return SharePreferencesManager(sharedPreferences)
    }

    @Provides
    @Singleton
    fun providesUserManager(sharePreferencesManager: SharePreferencesManager): UserManager {
        return UserManager(sharePreferencesManager)
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE
        )

}