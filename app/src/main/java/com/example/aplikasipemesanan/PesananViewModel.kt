package com.example.aplikasipemesanan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PesananViewModel (application: Application) : AndroidViewModel(application){
    private val repository: PesananRepository
    val allPesanan : LiveData<List<Pesanan>>

    init {
        val pesananDao = PesananRoomDatabase.getDatabase(application, viewModelScope).pesananDao()
        repository = PesananRepository(pesananDao)
        allPesanan = repository.allPesanan
    }
    fun insert(pesanan:Pesanan) = viewModelScope.launch {
        repository.insert(pesanan)
    }
}