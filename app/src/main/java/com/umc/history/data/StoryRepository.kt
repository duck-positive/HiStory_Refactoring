package com.umc.history.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class StoryRepository(private val storyDao: StoryDao) {
    val allStory : Flow<List<Story>> = storyDao.getAllStory()
    fun getStoryByCategory(category: String) = storyDao.getStoryByCategory(category)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertStory(story: Story){
        storyDao.insertStory(story)
    }
}