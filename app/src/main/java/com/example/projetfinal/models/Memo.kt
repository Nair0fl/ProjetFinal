package com.example.projetfinal.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo_table")

data class Memo(

    @PrimaryKey
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String

)