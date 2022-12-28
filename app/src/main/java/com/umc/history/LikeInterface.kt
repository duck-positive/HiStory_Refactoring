package com.umc.history

import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface LikeInterface {
    @POST("/common/story/liking/{postIdx}")
    fun postLike(
        @Header("Authorization") Authorization : String,
        @Path("postIdx") postIdx : Int
    ) : Call<DeleteResponse>
}