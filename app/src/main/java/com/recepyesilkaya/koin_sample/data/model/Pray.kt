package com.recepyesilkaya.koin_sample.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "pray_times")
data class Pray(
    @ColumnInfo(name = "Gunes")
    val Gunes: String,
    @ColumnInfo(name = "Imsak")
    val Imsak: String,
    @ColumnInfo(name = "Ogle")
    val Ogle: String,
    @ColumnInfo(name = "Ikindi")
    val Ikindi: String,
    @ColumnInfo(name = "Aksam")
    val Aksam: String,
    @ColumnInfo(name = "Yatsi")
    val Yatsi: String,
    @ColumnInfo(name = "MiladiTarihUzun")
    val MiladiTarihUzun: String,
    @ColumnInfo(name = "MiladiTarihKisa")
    val MiladiTarihKisa: String
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var pid: Long = 0
}