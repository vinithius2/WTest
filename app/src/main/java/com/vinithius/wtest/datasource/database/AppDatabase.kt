package com.vinithius.wtest.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vinithius.wtest.datasource.dao.CodigoPostalDAO
import com.vinithius.wtest.datasource.models.CodigoPostalEntity
import com.vinithius.wtest.datasource.models.CodigoPostalFTS

@Database(
    entities = [
        CodigoPostalEntity::class,
        CodigoPostalFTS::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun codigoPostalDao(): CodigoPostalDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "wtest_db"
                    ).allowMainThreadQueries() //Remover e usar Coroutines
                        .build()
                }
                return instance
            }
        }
    }
}