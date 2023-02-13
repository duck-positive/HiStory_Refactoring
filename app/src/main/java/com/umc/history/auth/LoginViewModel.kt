package com.umc.history.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val currentAccessToken : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}
