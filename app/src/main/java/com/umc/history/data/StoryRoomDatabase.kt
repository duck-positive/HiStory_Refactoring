package com.umc.history.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Story::class), version = 3, exportSchema = true)
@TypeConverters(StoryConverter::class)
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
                    Log.d("insert", "insert")
                    storyDao.insertStory(

                        Story(1, "daddd")
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