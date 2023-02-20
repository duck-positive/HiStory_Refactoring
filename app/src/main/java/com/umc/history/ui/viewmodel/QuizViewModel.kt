package com.umc.history.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.umc.history.data.QuizRepository

class QuizViewModel(private val repository: QuizRepository) : ViewModel() {
    private var _category : String? = null
    val category : String get() = _category!!

    private fun setCategory(category : String) {
        _category = category
    }

    fun getQuiz(category : String){
        setCategory(category)

    }
}

class QuizViewModelFactory(private val repository: QuizRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create (modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)){
            @Suppress("Unchecked")
            return QuizViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown")
    }
}
