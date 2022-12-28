package com.umc.history

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class AuthResponse(
    @SerializedName("timestamp") val timestamp : Timestamp,
    @SerializedName("status") val status : Int,
    @SerializedName("error") val error : String,
    @SerializedName("message") val message : String,
    @SerializedName("path") val path : String
)
data class TokenBody(
    @SerializedName("grantType") val grantType : String,
    @SerializedName("accessToken") val accessToken : String,
    @SerializedName("refreshToken") val refreshToken : String,
    @SerializedName("accessTokenExpiresIn") val accessTokenExpiresIn : String
)
data class LoginResponse(
    @SerializedName("status") val status : String,
    @SerializedName("body") val body : TokenBody
)
data class Token(
    @SerializedName("accessToken") val accessToken : String,
    @SerializedName("refreshToken") val refreshToken : String
)
