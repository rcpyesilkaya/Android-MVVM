package com.recepyesilkaya.koin_sample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.recepyesilkaya.koin_sample.data.model.Pray

@Database(entities = [Pray::class], version = 1)
abstract class RoomDatabase : RoomDatabase() {

    abstract fun prayDao(): PrayDao

}