package com.umc.history

import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body

interface CommentInterface {
    @GET("common/story/comment/{postId}")
    fun getComments(
        @Path("postId") postId : Int
    ) : Call<CommentResponse>

    @POST("common/story/comment/{postId}")
    fun postComment(
        @Header("Authorization") Authorization : String,
        @Path("postId") postId : Int,
        @Body contents : String
    ) : Call<StoryResponse>
}