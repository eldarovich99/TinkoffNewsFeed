package com.eldarovich99.tinkoffnews.di.components

import android.app.Application
import android.arch.persistence.room.Room
import com.eldarovich99.tinkoffnews.data.db.NewsDatabase
import com.eldarovich99.tinkoffnews.data.db.dao.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var application: Application){
    var database: NewsDatabase = Room.databaseBuilder(application, NewsDatabase::class.java, "notes-db").build()

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun providesNewsDatabase(application: Application) : NewsDatabase {
        return database
    }

    @Provides
    @Singleton
    fun providesNewssDao(): NewsDao {
        return database.newsDao()
    }

}