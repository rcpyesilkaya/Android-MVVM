package com.recepyesilkaya.koin_sample.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.recepyesilkaya.koin_sample.data.model.Pray

@Dao
interface PrayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPray(vararg pray: Pray)

    @Query("SELECT * FROM pray_times")
    fun getAllPrayTime(): List<Pray>?

    @Query("DELETE FROM pray_times")
    suspend fun deleteData()

}