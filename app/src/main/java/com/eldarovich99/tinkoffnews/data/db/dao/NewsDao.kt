package com.eldarovich99.tinkoffnews.data.db.dao

import android.arch.persistence.room.*
import com.eldarovich99.tinkoffnews.data.db.entity.FullNews
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fullNews: FullNews)

    @Query("SELECT * FROM newstitle ORDER BY publicationDate DESC")
    fun getAllNews(): Flowable<List<NewsTitle>>

    @Query("SELECT * FROM fullnews WHERE contentId = :id LIMIT 1")
    fun getContent(id:Int) : Flowable<FullNews>
}