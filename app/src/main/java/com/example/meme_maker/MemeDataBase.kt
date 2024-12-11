package com.example.meme_maker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Memes::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MemeDatabase : RoomDatabase() {
    abstract fun memesDao(): MemesDao

    companion object {
        @Volatile
        private var INSTANCE: MemeDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): MemeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MemeDatabase::class.java,
                    "meme_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
