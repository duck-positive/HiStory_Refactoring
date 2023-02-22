package com.umc.history.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.*

class StoryRepository(private val storyDao: StoryDao) {
    val allStory : Flow<List<Story>> = storyDao.getAllStory()
    val koreanStory = allStory
        .map {
            it.filter {
                it.category == "KOREAN"
            }
        }

    fun getStoryByCategory(category: String) : Flow<List<Story>> {
        return allStory.map { storyList ->
            storyList.filter { story ->
                story.category == category
            }
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertStory(story: Story){
        storyDao.insertStory(story)
    }
}