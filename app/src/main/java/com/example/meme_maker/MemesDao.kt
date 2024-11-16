package com.example.meme_maker

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface MemesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  saveMeme(memes: Memes)

    @Update
    suspend fun updateMeme(memes: Memes)

    @Delete
    suspend fun deleteMeme(memes: Memes)

    @Query("DELETE FROM meme_table WHERE id = :id")
    suspend fun deleteMemeById(id : Int)

    @Query("SELECT * FROM meme_table")
    fun getAllMeme() : Flow<List<Memes>>

}