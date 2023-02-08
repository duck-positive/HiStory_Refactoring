package com.umc.history.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.umc.history.OneStory

class KoreanHistoryViewModel : ViewModel() {
    private val _storyList = ArrayList<OneStory>()
    val storyList : ArrayList<OneStory>
        get() = _storyList


}