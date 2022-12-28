package com.umc.history

data class MyPageStory(
    var title: String? ="",
    var profileImg:String?,
    var likeNumber: Int?=null,
    var commentNumber: Int?=null,
    var detail: String? = "",
    var nickName: String? = ""
)
