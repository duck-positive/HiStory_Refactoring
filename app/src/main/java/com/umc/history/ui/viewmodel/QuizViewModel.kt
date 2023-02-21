package com.umc.history.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.umc.history.data.Quiz
import com.umc.history.data.QuizRepository

class QuizViewModel(private val repository: QuizRepository) : ViewModel() {
    private var _category : String? = null
    val category : String get() = _category!!
    private var _quizList = repository.getQuizByCategory("ALL").asLiveData()
    val quizList get() = _quizList
    private fun setCategory(category : String) {
        _category = category
    }
    fun removeSolvedQuiz(){
        Log.d("quiz_remove", "${_quizList.value}")
        _quizList.value!!.removeAt(0)
    }
    fun getQuiz(category : String){
        setCategory(category)
        _quizList = repository.getQuizByCategory(category).asLiveData()
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
