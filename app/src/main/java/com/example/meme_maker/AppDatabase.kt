package com.example.meme_maker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Memes::class, Templates::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun memesDao() : MemesDao
    abstract fun templatesDao() : TemplatesDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context : Context) : AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,
                    "MemesDatabase").build()
                INSTANCE = instance
                return instance
            }
        }
    }

}