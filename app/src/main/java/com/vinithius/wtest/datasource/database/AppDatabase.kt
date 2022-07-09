package com.vinithius.wtest.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vinithius.wtest.datasource.dao.CodigoPostalDAO
import com.vinithius.wtest.datasource.models.CodigoPostalEntity

@Database(entities = [CodigoPostalEntity::class], version = 1)
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
                    ).build()
                }
                return instance
            }
        }
    }
}