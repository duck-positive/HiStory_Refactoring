package com.umc.history.data

import android.util.Log
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.*

class StoryRepository(private val storyDao: StoryDao) {
    val allStory : Flow<List<Story>> = storyDao.getAllStory()

    fun getStoryByCategory(category: String) : Flow<List<Story>> {
        return allStory.map { storyList ->
            storyList.filter { story ->
                story.category == category
            }
        }
    }
    fun getStoryWriteByUser(userId: Long) : Flow<List<Story>> {
        Log.d("userIdww", "${userId.toString()}")
        return allStory.map { storyList ->
            storyList.filter {
                it.userId == userId
            }
        }
    }
    fun getLikedStoryByUser(){}

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertStory(story: Story){
        storyDao.insertStory(story)
    }
}