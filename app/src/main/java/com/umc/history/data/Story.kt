package com.umc.history.data

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "story_table")
data class Story(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "category") var category: String,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "content") var content: String? =null,
    @ColumnInfo(name = "img") val img: List<Bitmap>? = null,
    @ColumnInfo(name = "hashTag") val hashTag : List<String>? = null,
    @ColumnInfo(name = "likeNumber") var likeNumber: Int = 0,
    @ColumnInfo(name = "commentNumber") var commentNumber: Int = 0

)
