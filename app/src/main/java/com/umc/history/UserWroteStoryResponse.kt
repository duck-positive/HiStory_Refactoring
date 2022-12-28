package com.umc.history

import com.google.gson.annotations.SerializedName

data class UserWroteStoryResponse (
    @SerializedName("contents") val contents : String,
    @SerializedName("title") val title : String,
    @SerializedName("category") val category : String,
    @SerializedName("user") val user : WroteUser?,
    @SerializedName("totalComment") val totalComment : Int,
    @SerializedName("totalClick") val totalClick : Int,
    @SerializedName("postIdx") val postIdx : Int,
    @SerializedName("totalLike") val totalLike : Int,
    @SerializedName("createdDate") val createdDate : String,
    @SerializedName("lastModifedDate") val lastModifedDate : String,
        )
data class GetUserWroteStoryResponse(
    @SerializedName("status") val status: String,
    @SerializedName("body") val body : UserWroteStoryResponse?
)
data class WroteUser(
    @SerializedName("userId") val userIdx : Int,
    @SerializedName("nickName") val nickName : String,
    @SerializedName("profileImgUrl") val profileImageUrl : String?,
)