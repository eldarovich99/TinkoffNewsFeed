package com.eldarovich99.tinkoffnews.data.db.repository

import android.content.Context
import android.support.annotation.WorkerThread
import android.widget.Toast
import com.eldarovich99.tinkoffnews.data.db.dao.NewsDao
import com.eldarovich99.tinkoffnews.data.db.entity.News
import com.eldarovich99.tinkoffnews.data.db.entity.Response
import com.eldarovich99.tinkoffnews.data.network.TinkoffApi
import com.eldarovich99.tinkoffnews.data.network.TinkoffClient
import com.eldarovich99.tinkoffnews.data.network.deserializers.TitleResponseDeserializer
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
        .getRetrofitInstance(Response::class.java, TitleResponseDeserializer())
        .create(TinkoffApi::class.java)
    val gson = GsonBuilder().create()
    @WorkerThread
    fun insert(news: News){
        newsDao.insert(news)
    }
    @WorkerThread
    fun delete(news: News){
        newsDao.delete(news)
    }
    @WorkerThread
    fun getNews() : Flowable<List<News>> {
        val factory = RxJava2CallAdapterFactory.create()
        return newsDao.getAllNews()
    }
    @WorkerThread
    fun getContentFromDB(id:String) : Flowable<News> {
        return newsDao.getContent(id)
    }

    fun getNewsList(context: Context): Observable<List<News>>{
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
//            .doOnError{
//                Toast.makeText(context, "Проверьте сетевое подключение", Toast.LENGTH_SHORT).show()
//            }
//            .onErrorResumeNext(getNews().toObservable())
    }

    fun getContent(context: Context, id: String) : Observable<List<News>>{
        return titleApi.getContent(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext{news -> launchInsertion(news)}
            .doOnError{
                Toast.makeText(context, "Проверьте сетевое подключение", Toast.LENGTH_SHORT).show()
            }
    }

    private fun launchInsertion(news:List<News>){
        Completable.fromAction{ newsDao.insert(news) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}