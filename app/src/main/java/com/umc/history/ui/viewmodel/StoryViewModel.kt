package com.umc.history.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.umc.history.data.Story
import com.umc.history.data.StoryRepository
import kotlinx.coroutines.launch

class StoryViewModel(private val repository: StoryRepository) : ViewModel() {
    val allStory = repository.allStory
    val koreanStory = repository.koreanStory
    val orientalStory : LiveData<List<Story>> = getStoryByCategory("ORIENTAL")
    val westernStory : LiveData<List<Story>> = getStoryByCategory("WESTERN")
    private fun getStoryByCategory(category : String) : LiveData<List<Story>>{
        Log.d("collext", "dad")
        return repository.getStoryByCategory(category).asLiveData()
    }
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




