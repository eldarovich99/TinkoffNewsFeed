package com.eldarovich99.tinkoffnews.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.eldarovich99.tinkoffnews.data.db.entity.News

@Dao
interface NewsDao {
    @Insert
    fun insert(news: News)
    @Delete
    fun delete(news:News)
    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<News>>
}