package com.hardbobby.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hardbobby.data.database.entity.WebSocketEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WebSocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateWebSocket(webSocketEntity: WebSocketEntity)

    @Query("SELECT * from WebSocket order by timeStamp Limit 1")
    fun getWebSocket(): Flow<WebSocketEntity>

    @Query("SELECT * from WebSocket order by timeStamp Limit 1")
    fun getListWebSocket(): Flow<List<WebSocketEntity>>

    @Query("DELETE FROM WebSocket where type = :type")
    suspend fun deleteOldWebSocket(type: Int)

}