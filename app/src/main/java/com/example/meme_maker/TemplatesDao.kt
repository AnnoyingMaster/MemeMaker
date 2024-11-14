package com.example.meme_maker

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface TemplatesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  saveTemplate(templates: Templates)

    @Update
    suspend fun updateTemplate(templates: Templates)

    @Delete
    suspend fun deleteTemplate(templates: Templates)

    @Query("DELETE FROM template_table WHERE id = :id AND default_template = '0'")
    suspend fun deleteTemplateById(id : Int)

    @Query("SELECT * FROM template_table")
    fun getAllTemplate() : Flow<List<Templates>>

}