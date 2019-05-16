package com.eldarovich99.tinkoffnews.data.db.repository

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.eldarovich99.tinkoffnews.data.db.dao.NewsDao
import com.eldarovich99.tinkoffnews.data.db.entity.News
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsDao: NewsDao) {
    val allNews: Flowable<List<News>> = newsDao.getAllNews()

    @WorkerThread       // called on a worker thread
    fun insert(news: News){     // the modifier means that a function can be interrupted and then continued
        newsDao.insert(news)
    }
    @WorkerThread
    fun delete(news: News){
        newsDao.delete(news)
    }
    @WorkerThread
    fun getNews(id:Int) : Flowable<List<News>> {
        return newsDao.getAllNews()
    }

}