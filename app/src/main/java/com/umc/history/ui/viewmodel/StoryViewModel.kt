package com.umc.history.ui.viewmodel

import androidx.lifecycle.*
import com.umc.history.Story
import com.umc.history.data.StoryRepository
import kotlinx.coroutines.launch

class StoryViewModel(private val repository: StoryRepository) : ViewModel() {
    val allStory : LiveData<List<Story>> = repository.allStory.asLiveData()
    val koreanStory : LiveData<List<Story>> = repository.getStoryByCategory("KOREAN").asLiveData()
    val orientalStory : LiveData<List<Story>> = repository.getStoryByCategory("ORIENTAL").asLiveData()
    val westernStory : LiveData<List<Story>> = repository.getStoryByCategory("WESTERN").asLiveData()

    fun insertStory(story: Story) = viewModelScope.launch {
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




