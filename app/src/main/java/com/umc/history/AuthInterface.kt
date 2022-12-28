package com.umc.history

import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body

interface AuthInterface {
    @GET("/user/sign/{userID}/nickNameExist")
    fun nickNameExist(@Path("userID") userID:String?) : Call<ExistResponse>

    @GET("/user/sign/{userId}/exist")
    fun userIdExist(@Path("userId")userId:String?) : Call<ExistResponse>

    @POST("/user/sign")
    fun signUp(@Body user : User) : Call<AuthResponse>

    @POST("/user/login")
    fun login(@Body login : Login) : Call<LoginResponse>

    @POST("/user/reissue")
    fun tokenReissue(@Body token : Token) : Call<LoginResponse>

}