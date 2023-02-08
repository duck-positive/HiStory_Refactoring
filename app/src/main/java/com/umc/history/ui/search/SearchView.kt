package com.umc.history.ui.search

import retrofit2.http.Body

interface earchView {
    fun onSearchLoading()
    fun onSearchSuccess(body : List<Body>?)
    fun onSearchFailure()
}