package com.umc.history

import com.google.gson.annotations.SerializedName

data class WritedStroyResponse (
    @SerializedName("storyIdx") val storyIdx : Int,
    @SerializedName("title") val title : String,
    @SerializedName("comments") val comments : Int,
    @SerializedName("likes") val likes : Int,
    @SerializedName("content") val content : String,
    @SerializedName("nickname") val nickname : String,
    @SerializedName("profile") val profile : String
)