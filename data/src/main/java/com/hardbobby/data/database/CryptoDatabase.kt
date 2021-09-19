package com.hardbobby.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hardbobby.data.database.dao.CryptoDao
import com.hardbobby.data.database.dao.WebSocketDao
import com.hardbobby.data.database.entity.Crypto
import com.hardbobby.data.database.entity.WebSocketEntity

@Database(entities = [Crypto::class, WebSocketEntity::class], version = 1)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao
    abstract fun webSocketDao(): WebSocketDao
}