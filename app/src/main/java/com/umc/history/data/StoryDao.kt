package com.umc.history.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.umc.history.Story
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Query("SELECT * FROM story_table")
    fun getAllStory() : Flow<List<Story>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(story: Story)
}