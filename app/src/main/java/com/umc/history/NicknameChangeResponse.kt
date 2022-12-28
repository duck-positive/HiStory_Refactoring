package com.umc.history

import com.google.gson.annotations.SerializedName

data class NicknameChangeResponse(
    @SerializedName("status") val status : String,
    @SerializedName("body") val body : String
)