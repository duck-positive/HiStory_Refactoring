package com.umc.history.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Story::class, Quiz::class] , version = 2, exportSchema = true)
@TypeConverters(StoryConverter::class)
public abstract class AppRoomDatabase : RoomDatabase(){
    abstract fun storyDao() : StoryDao
    abstract fun quizDao() : QuizDao

    private class StoryDatabaseCallback(
        private val scope : CoroutineScope
    ) : Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var storyDao = database.storyDao()
                    Log.d("insert", "insert")

                }
            }
        }
    }
    companion object {
        @Volatile
        private var INSTANCE : AppRoomDatabase? = null

        fun getDatabase(
            context : Context,
            scope : CoroutineScope
        ) : AppRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "app_database"
                )
                    .addCallback(StoryDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}