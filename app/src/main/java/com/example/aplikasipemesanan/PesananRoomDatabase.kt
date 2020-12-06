package com.example.aplikasipemesanan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Pesanan::class), version = 1, exportSchema = false)
public abstract class PesananRoomDatabase : RoomDatabase() {
    abstract fun pesananDao(): PesananDao
    private class PesananDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.pesananDao())
                }
            }
        }
        suspend fun populateDatabase(pesananDao: PesananDao) {
            pesananDao.deleteAll()
            var pesanan = Pesanan("Rendi, Nasi Goreng")
            pesananDao.insert(pesanan)
            pesanan = Pesanan("Yudi, Mie Goreng dan Teh Panas")
            pesananDao.insert(pesanan)
            // TODO: Add your own words!
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PesananRoomDatabase? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PesananRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PesananRoomDatabase::class.java,
                    "pesanan_database"
                )
                    .addCallback(PesananDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}