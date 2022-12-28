package com.umc.history

interface CommentView {
    fun onCommentLoading()
    fun onCommentSuccess(status : String, body : List<Comment?>)
    fun onCommentFailure()
}