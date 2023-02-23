package com.umc.history.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User
import com.umc.history.data.Story
import com.umc.history.data.StoryRepository
import kotlinx.coroutines.flow.Flow


class MyPageViewModel(val repository: StoryRepository) : ViewModel() {
    private val _storyWriteByUser : Flow<List<Story>> = repository.getStoryWriteByUser(2)
    val storyWriteByUser get() = _storyWriteByUser
}

class MyPageViewModelFactory(private val repository: StoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyPageViewModel::class.java)){
            return MyPageViewModel(repository) as T
        }
        throw IllegalArgumentException("error")
    }
}

