package com.umc.history

interface TestView {
    fun onGetTestLoading()
    fun onGetTestSuccess(body : List<Test>?)
    fun onGetTestFailure()
}