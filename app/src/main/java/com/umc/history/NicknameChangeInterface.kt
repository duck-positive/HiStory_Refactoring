package com.umc.history


import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.Call

interface NicknameChangeInterface {
    @PATCH("/user/changeNickName")
    fun changeNickname(@Body nicknameChangeRequest: NicknameChangeRequest) : Call<NicknameChangeResponse>
}