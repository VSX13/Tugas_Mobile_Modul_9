package com.example.aplikasipemesanan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "pesanan_table")
data class Pesanan (@PrimaryKey @ColumnInfo(name = "Pesanan") val pesanan: String)