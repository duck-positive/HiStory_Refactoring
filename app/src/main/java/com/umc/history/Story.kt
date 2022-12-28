package com.umc.history

import android.net.Uri
import retrofit2.http.Url

data class Story(
    var title: String? = "",
    var coverImg: String,
    var likeNumber: Int?=null,
    var commentNumber: Int?=null,
    var detail: ArrayList<String>?=null
)
