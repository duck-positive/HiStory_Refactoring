package com.umc.history

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface LikedStoryInterface {
    @GET("/common/stories/liking")
    fun getLikedStory(@Header("Authorization") authorization : String):Call<GetLikedStoryResponse>
}
