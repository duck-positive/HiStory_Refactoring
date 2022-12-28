package com.umc.history

import retrofit2.http.Body
import retrofit2.http.GET

interface ProfileInterface {
    @GET
    fun getProfile()
}