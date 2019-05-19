package com.eldarovich99.tinkoffnews.data.db.repository

import android.support.annotation.WorkerThread
import com.eldarovich99.tinkoffnews.data.db.dao.NewsDao
import com.eldarovich99.tinkoffnews.data.db.entity.ContentResponse
import com.eldarovich99.tinkoffnews.data.db.entity.FullNews
import com.eldarovich99.tinkoffnews.data.db.entity.NewsTitle
import com.eldarovich99.tinkoffnews.data.db.entity.TitleResponse
import com.eldarovich99.tinkoffnews.data.network.TinkoffApi
import com.eldarovich99.tinkoffnews.data.network.TinkoffClient
import com.eldarovich99.tinkoffnews.data.network.deserializers.FullResponseDeserializer
import com.eldarovich99.tinkoffnews.data.network.deserializers.TitleResponseDeserializer
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsDao: NewsDao) {
    val titleApi = TinkoffClient.Instance
        .getRetrofitInstance(TitleResponse::class.java, TitleResponseDeserializer())
        .create(TinkoffApi::class.java)
    val contentApi = TinkoffClient.Instance
        .getRetrofitInstance(ContentResponse::class.java, FullResponseDeserializer())
        .create(TinkoffApi::class.java)

    @WorkerThread
    fun insert(fullNews: FullNews){
        newsDao.insert(fullNews)
    }
    @WorkerThread
    fun delete(newsTitle: NewsTitle){
        newsDao.delete(newsTitle)
    }
    @WorkerThread
    fun getNewsFromDB() : Flowable<List<NewsTitle>> {
        return newsDao.getAllNews()
    }
    @WorkerThread
    fun getContentFromDB(id:Int) : Flowable<FullNews> {
        return newsDao.getContent(id)
    }

    fun getNewsList(): Observable<List<NewsTitle>>{
        return titleApi.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                val data = response.payload.toMutableList()
                data.sortByDescending { it.publicationDate }
                val list = data.toList()
                launchInsertion(list)
                list
            }
    }

    fun getContent(id: Int) : Observable<FullNews>{
        return contentApi.getContent(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                val full = response.payload
                launchInsertion(full)
                full
            }
    }

    private fun launchInsertion(newsTitles:List<NewsTitle>){
        Completable.fromAction{ newsDao.insert(newsTitles) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    private fun launchInsertion(fullNews: FullNews){
        Completable.fromAction{ newsDao.insert(fullNews) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}