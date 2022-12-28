package com.umc.history

import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("status") val status : String,
    @SerializedName("body") val body : List<Comment?>
)
data class Comment(
    @SerializedName("createdDate") val createdDate : String,
    @SerializedName("lastModifedDate") val lastModifedDate : String,
    @SerializedName("commentIdx") val commentIdx : Int,
    @SerializedName("user") val commentUser : CommentUser,
    @SerializedName("contents") val contents : String
)
data class CommentUser(
    @SerializedName("createdDate") val createdDate : String,
    @SerializedName("lastModifedDate") val lastModifedDate : String,
    @SerializedName("userIdx") val userIdx : Int,
    @SerializedName("nickName") val nickName : String,
    @SerializedName("userId") val userId : String,
    @SerializedName("profileImgUrl") val profileImageUrl : String?,
    @SerializedName("authority") val authority : String
)