package com.umc.history

interface LikeView {
    fun onLikeLoading()
    fun onLikeSuccess(body : Boolean)
    fun onLikeFailure()
}