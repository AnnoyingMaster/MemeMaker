package com.example.meme_maker

import Converters
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(version = 1, entities = [Templates::class])
@TypeConverters(Converters::class)
abstract class TemplateDatabase : RoomDatabase() {

    abstract fun templatesDao() : TemplatesDao

    companion object{
        @Volatile
        private var INSTANCE : TemplateDatabase? = null

        @JvmStatic
        fun getDatabase(context : Context) : TemplateDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    TemplateDatabase::class.java,
                    "MemesDatabase").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}