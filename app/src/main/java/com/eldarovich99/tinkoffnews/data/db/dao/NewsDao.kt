package com.eldarovich99.tinkoffnews.data.db.dao

import android.arch.persistence.room.*
import com.eldarovich99.tinkoffnews.data.db.entity.News
import io.reactivex.Flowable

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: News)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(news: List<News>)

    @Delete
    fun delete(news:News)

    @Query("SELECT * FROM news")
    fun getAllNews(): Flowable<List<News>>

    @Query("SELECT * FROM news WHERE id = :id")
    fun getContent(id:String) : Flowable<News>
}