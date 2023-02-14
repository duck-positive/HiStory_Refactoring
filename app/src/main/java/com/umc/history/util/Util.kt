package com.umc.history.util

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

interface Util {
    fun hideKeyboard(editText: EditText, context : Context){
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }
}