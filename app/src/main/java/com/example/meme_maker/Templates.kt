package com.example.meme_maker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "template_table")
data class Templates(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "file_name") val name : String,
    @ColumnInfo(name = "created_at") val creationDate : Date,
    @ColumnInfo(name = "default_template") val default : Int,
)
