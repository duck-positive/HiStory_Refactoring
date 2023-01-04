package com.umc.history.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val currentAccessToken : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}
