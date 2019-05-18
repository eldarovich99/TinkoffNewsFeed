package com.eldarovich99.tinkoffnews.data.db.repository

import android.support.annotation.WorkerThread
import com.eldarovich99.tinkoffnews.data.db.dao.NewsDao
import com.eldarovich99.tinkoffnews.data.db.entity.News
import com.eldarovich99.tinkoffnews.data.network.TinkoffClient
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

    fun getNewsList(): Observable<List<News>>{
        val api = TinkoffClient.Instance.api
        return api.getResponse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                val data = response.payload.toMutableList()
                data.sortByDescending { it.publicationDate.milliseconds }
                data.toList()
            }
    }

}