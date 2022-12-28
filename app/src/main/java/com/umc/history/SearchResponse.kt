package com.umc.history

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("status") val status : String,
    @SerializedName("body") val body : List<Body>?
)
data class Body(
    @SerializedName("postIdx") val postIdx: Int,
    @SerializedName("totalLike") val totalLike: Int,
    @SerializedName("totalClick") val totalClick: Int,
    @SerializedName("totalComment") val totalComment: Int,
    @SerializedName("createDate") val createDate: String,
    @SerializedName("lastModifiedDate") val lastModifiedDate: String,
    @SerializedName("category") val category: String,
    @SerializedName("title") val title: String,
    @SerializedName("user") val user: User2?,
    @SerializedName("contents") val contents : String
)
data class User2(
    @SerializedName("profileImgUrl") val profileImgUrl : String?,
    @SerializedName("userId") val userId : String,
    @SerializedName("nickName") val nickName : String?
)