package com.umc.history.data

import kotlinx.coroutines.flow.Flow

class QuizRepository(private val quizDao: QuizDao) {
    fun getQuizByCategory(category : String) : Flow<MutableList<Quiz>> = quizDao.getQuizByCategory(category)
}