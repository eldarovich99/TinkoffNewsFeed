package com.eldarovich99.tinkoffnews.data.db.dao

import android.arch.persistence.room.*
import com.eldarovich99.tinkoffnews.data.db.entity.NewsTitle
import io.reactivex.Flowable

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(newsTitle: NewsTitle)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(newsTitles: List<NewsTitle>)

    @Delete
    fun delete(newsTitle:NewsTitle)

    @Query("SELECT * FROM newstitle")
    fun getAllNews(): Flowable<List<NewsTitle>>

    @Query("SELECT * FROM newstitle WHERE id = :id")
    fun getContent(id:String) : Flowable<NewsTitle>
}