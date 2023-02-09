package com.umc.history

import android.app.Application
import com.umc.history.data.StoryRepository
import com.umc.history.data.StoryRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HiStoryApplication() : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { StoryRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { StoryRepository(database.storyDao()) }
}