package com.example.aplikasipemesanan

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PesananDao {

    @Query("SELECT * from pesanan_table ORDER BY pesanan ASC")
    fun getAlphabetizedPesanan(): LiveData<List<Pesanan>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pesanan: Pesanan)

    @Query("DELETE FROM pesanan_table")
    suspend fun deleteAll()
}