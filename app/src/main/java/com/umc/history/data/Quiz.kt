package com.umc.history.data

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "quiz_table")
data class Quiz(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "category") val category : String,
    @ColumnInfo(name = "question") val question : String,
    @ColumnInfo(name = "answer") val answer : Boolean,
    @ColumnInfo(name = "explanation") val explanation : String
)
