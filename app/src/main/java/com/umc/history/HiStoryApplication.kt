package com.umc.history

import android.app.Application
import com.umc.history.data.AppRoomDatabase
import com.umc.history.data.QuizRepository
import com.umc.history.data.StoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HiStoryApplication() : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppRoomDatabase.getDatabase(this, applicationScope) }
    val storyRepository by lazy { StoryRepository(database.storyDao()) }
    val quizRepository by lazy { QuizRepository(database.quizDao()) }
}