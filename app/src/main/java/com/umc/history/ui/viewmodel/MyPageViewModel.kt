package com.umc.history.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User
import com.umc.history.data.Story
import com.umc.history.data.StoryRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MyPageViewModel(private val repository: StoryRepository) : ViewModel() {
    private var _userId : Long = 0
    val userId get() = _userId

    private lateinit var _storyWriteByUser : Flow<List<Story>>
    val storyWriteByUser get() = _storyWriteByUser
    fun getStory(userId : Long){
        _storyWriteByUser = repository.getStoryWriteByUser(userId)
    }
     fun getStoryWriteByUser(userId : Long) {
         _userId = userId
         getStory(userId)
    }
}

class MyPageViewModelFactory(private val repository: StoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyPageViewModel::class.java)){
            return MyPageViewModel(repository) as T
        }
        throw IllegalArgumentException("error")
    }
}

