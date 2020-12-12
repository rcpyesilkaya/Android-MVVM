package com.recepyesilkaya.koin_sample.di

import androidx.room.Room
import com.recepyesilkaya.koin_sample.data.local.RoomDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(androidApplication(), RoomDatabase::class.java, "prayDatabase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<RoomDatabase>().prayDao() }
}