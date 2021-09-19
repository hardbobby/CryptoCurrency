package com.hardbobby.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hardbobby.data.database.entity.Crypto
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateCrypto(crypto: Crypto)

    @Query("SELECT * from Crypto where pageNo = :pageNo")
    fun getListCrypto(pageNo: Int): Flow<List<Crypto>>

    @Query("DELETE FROM Crypto where pageNo = :pageNo")
    suspend fun deleteOldCrypto(pageNo: Int)
}