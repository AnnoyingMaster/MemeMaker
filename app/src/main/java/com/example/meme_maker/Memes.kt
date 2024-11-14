package com.example.meme_maker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "meme_table")
data class Memes(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "file_name") val name : String,
    @ColumnInfo(name = "file_path") val path : String,
    @ColumnInfo(name="created_at") val creationDate : Date,
)
