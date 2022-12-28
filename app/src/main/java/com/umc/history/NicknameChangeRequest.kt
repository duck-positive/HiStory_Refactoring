package com.umc.history

import com.google.gson.annotations.SerializedName

data class NicknameChangeRequest (
    @SerializedName("id") val id : String,
    @SerializedName("nickname") val nickname : String
    )