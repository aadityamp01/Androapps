package com.example.passwordmanager.Data

import android.content.Context
import android.view.KeyCharacterMap
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Key::class],version = 1)
abstract class KeyDatabase :RoomDatabase(){

    abstract fun keyDao(): KeyDao

    companion object{
        @Volatile
        private var INSTANCE:KeyDatabase? = null

        fun getDatabase(context: Context):KeyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KeyDatabase::class.java,
                    "key_database"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }
}