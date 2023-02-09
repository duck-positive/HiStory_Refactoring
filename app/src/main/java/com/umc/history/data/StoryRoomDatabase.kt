package com.umc.history.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.umc.history.Story
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Story::class), version = 1, exportSchema = false)
public abstract class StoryRoomDatabase : RoomDatabase(){
    abstract fun storyDao() : StoryDao

    private class StoryDatabaseCallback(
        private val scope : CoroutineScope
    ) : Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var storyDao = database.storyDao()
                    storyDao.insert(
                        Story(1)
                    )
                }
            }
        }
    }
    companion object {
        @Volatile
        private var INSTANCE : StoryRoomDatabase? = null

        fun getDatabase(
            context : Context,
            scope : CoroutineScope
        ) : StoryRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StoryRoomDatabase::class.java,
                    "story_database"
                )
                    .addCallback(StoryDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}