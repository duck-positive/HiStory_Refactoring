package com.umc.history

interface AuthView {
    fun onAuthLoading()
    fun onAuthSuccess(tokenBody: TokenBody)
    fun onAuthFailure()
}