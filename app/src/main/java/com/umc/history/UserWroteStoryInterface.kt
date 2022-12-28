package com.umc.history

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserWroteStoryInterface {
    @GET("/common/stories/byUser")
    fun getUserWroteStory(@Header("Authorization") Authorization: String): Call<SearchResponse>

}