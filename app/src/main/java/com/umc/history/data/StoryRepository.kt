package com.umc.history.data

import androidx.annotation.WorkerThread
import com.umc.history.Story
import kotlinx.coroutines.flow.Flow

class StoryRepository(private val storyDao: StoryDao) {
    val allStory : Flow<List<Story>> = storyDao.getAllStory()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertStory(story: Story){
        storyDao.insert(story)
    }
}