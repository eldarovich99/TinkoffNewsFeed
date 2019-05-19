package com.eldarovich99.tinkoffnews.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.eldarovich99.tinkoffnews.data.db.dao.NewsDao
import com.eldarovich99.tinkoffnews.data.db.entity.FullNews
import com.eldarovich99.tinkoffnews.data.db.entity.NewsTitle

@Database(entities = [NewsTitle::class, FullNews::class], version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}