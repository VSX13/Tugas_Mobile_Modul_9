package com.example.aplikasipemesanan

import androidx.lifecycle.LiveData

class PesananRepository(private val pesananDao: PesananDao) {
    val allPesanan: LiveData<List<Pesanan>> = pesananDao.getAlphabetizedPesanan()
    suspend fun insert(pesanan: Pesanan) {
        pesananDao.insert(pesanan)
    }
}