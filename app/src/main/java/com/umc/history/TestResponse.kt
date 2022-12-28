package com.umc.history

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetTestResponse(
    @SerializedName("status") val status : String,
    @SerializedName("body") val body : List<Test>?
    ) : Parcelable

@Parcelize
data class Test(
    @SerializedName("quizIdx") val quizIdx : Int,
    @SerializedName("category") val category : String,
    @SerializedName("question") val question : String,
    @SerializedName("answer") val answer : Boolean,
    @SerializedName("solution") val solution : String
) : Parcelable