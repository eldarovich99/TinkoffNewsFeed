package com.eldarovich99.tinkoffnews.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.eldarovich99.tinkoffnews.data.db.dao.NewsDao
import com.eldarovich99.tinkoffnews.data.db.entity.News

@Database(entities = [News::class], version = 1)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
    companion object {
        @Volatile  // means that this object will not be cached
        private var INSTANCE: NotesDatabase? = null
        fun getDatabase(context: Context): NotesDatabase {
            return INSTANCE ?: synchronized(this){       // this block of code will be executed only on a single thread simultaneously
                val instance = Room.databaseBuilder(context.applicationContext,
                    NotesDatabase::class.java,
                    "Notes_database").build()
                INSTANCE = instance
                instance
            }
        }

    }
}