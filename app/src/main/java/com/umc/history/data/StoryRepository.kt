package com.umc.history.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class StoryRepository(private val storyDao: StoryDao) {
    val allStory : Flow<List<Story>> = storyDao.getAllStory()
    val koreanStory : Flow<List<Story>> = storyDao.getStoryByCategory("Korean")
    fun getStoryByCategory(category: String) = storyDao.getStoryByCategory(category)
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertStory(story: Story){
        storyDao.insert(story)
    }
}