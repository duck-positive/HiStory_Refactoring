package com.umc.history.data

import android.graphics.Bitmap
import androidx.room.TypeConverter
import com.google.gson.Gson

class StoryConverter {
    @TypeConverter
    fun imageListToJson(imageList : List<Bitmap>?) = Gson().toJson(imageList)

    @TypeConverter
    fun jsonToImageList(value : String) = Gson().fromJson(value, Array<Bitmap>::class.java).toList()

    @TypeConverter
    fun stringListToJson(hashTagList : List<String>?) = Gson().toJson(hashTagList)

    @TypeConverter
    fun jsonToStringList(value : String) = Gson().fromJson(value, Array<String>::class.java).toList()
}