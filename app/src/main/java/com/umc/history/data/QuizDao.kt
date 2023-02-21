package com.umc.history.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz_table WHERE category =:category")
    fun getQuizByCategory(category : String) : Flow<MutableList<Quiz>>
}