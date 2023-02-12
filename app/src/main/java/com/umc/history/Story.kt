package com.umc.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "story_table")
data class Story(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "category")
    var category: String,
    @ColumnInfo(name = "title")
    var title: String = "",
    @ColumnInfo(name = "coverImg")
    val coverImg: String = "",
    @ColumnInfo(name = "likeNumber")
    var likeNumber: Int = 0,
    @ColumnInfo(name = "commentNumber")
    var commentNumber: Int = 0,
    @ColumnInfo(name = "detail")
    var detail: String? =null
)
