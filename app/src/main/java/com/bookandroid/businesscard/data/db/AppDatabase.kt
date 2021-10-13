package com.bookandroid.businesscard.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bookandroid.businesscard.data.model.BusinessCard

@Database(entities = [BusinessCard::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun businessDao(): Dao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "businesscard_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}