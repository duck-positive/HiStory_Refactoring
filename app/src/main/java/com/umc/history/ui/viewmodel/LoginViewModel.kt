package com.umc.history.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val currentAccessToken : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}
