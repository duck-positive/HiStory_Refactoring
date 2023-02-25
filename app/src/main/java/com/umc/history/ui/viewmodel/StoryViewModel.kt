package com.umc.history.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.umc.history.data.Story
import com.umc.history.data.StoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StoryViewModel(private val repository: StoryRepository) : ViewModel() {
    private val _allStory = repository.allStory
    val allStory get() = _allStory

    private val _koreanStory = repository.getStoryByCategory("KOREAN")
    val koreanStory get() = _koreanStory

    private val _orientalStory = repository.getStoryByCategory("ORIENTAL")
    val orientalStory get() = _orientalStory

    private val _westernStory = repository.getStoryByCategory("WESTERN")
    val westernStory get() = _westernStory
    suspend fun insertStory(story: Story) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertStory(story)
    }

}
class StoryViewModelFactory(private val repository: StoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoryViewModel::class.java)){
            @Suppress("Unchecked")
            return StoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown")
    }
}




