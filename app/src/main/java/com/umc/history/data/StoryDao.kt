package com.umc.history.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Query("SELECT * FROM story_table")
    fun getAllStory() : Flow<List<Story>>

    @Query("SELECT * FROM story_table WHERE category = :category")
    fun getStoryByCategory(category: String) : Flow<List<Story>>

    @Query("SELECT * FROM story_table WHERE userId = :userId")
    fun getStoryWriteByUser(userId : Long) : Flow<List<Story>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(story: Story)
}