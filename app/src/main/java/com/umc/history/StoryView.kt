package com.umc.history

interface StoryView {
    fun onStoryLoading()
    fun onStorySuccess(status : String, body : List<OneStory>)
    fun onStoryFailure()
}

interface OneStoryView{
    fun onStoryLoading()
    fun onStorySuccess(status : String, body : OneStory?)
    fun onStoryFailure()
}