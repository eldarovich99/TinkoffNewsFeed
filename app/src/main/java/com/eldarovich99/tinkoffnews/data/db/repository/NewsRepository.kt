package com.eldarovich99.tinkoffnews.data.db.repository

import android.content.Context
import android.support.annotation.WorkerThread
import android.widget.Toast
import com.eldarovich99.tinkoffnews.data.db.dao.NewsDao
import com.eldarovich99.tinkoffnews.data.db.entity.ContentResponse
import com.eldarovich99.tinkoffnews.data.db.entity.NewsTitle
import com.eldarovich99.tinkoffnews.data.db.entity.TitleResponse
import com.eldarovich99.tinkoffnews.data.network.TinkoffApi
import com.eldarovich99.tinkoffnews.data.network.TinkoffClient
import com.eldarovich99.tinkoffnews.data.network.deserializers.FullResponseDeserializerDeserializer
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
        .getRetrofitInstance(TitleResponse::class.java, TitleResponseDeserializer())
        .create(TinkoffApi::class.java)
    val contentApi = TinkoffClient.Instance
        .getRetrofitInstance(ContentResponse::class.java, FullResponseDeserializerDeserializer())
        .create(TinkoffApi::class.java)

    val gson = GsonBuilder().create()
    @WorkerThread
    fun insert(newsTitle: NewsTitle){
        newsDao.insert(newsTitle)
    }
    @WorkerThread
    fun delete(newsTitle: NewsTitle){
        newsDao.delete(newsTitle)
    }
    @WorkerThread
    fun getNews() : Flowable<List<NewsTitle>> {
        val factory = RxJava2CallAdapterFactory.create()
        return newsDao.getAllNews()
    }
    @WorkerThread
    fun getContentFromDB(id:String) : Flowable<NewsTitle> {
        return newsDao.getContent(id)
    }

    fun getNewsList(context: Context): Observable<List<NewsTitle>>{
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

    fun getContent(context: Context, id: Int) : Observable<ContentResponse>{
        return contentApi.getContent(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            //.doOnNext{news -> launchInsertion(news)}
            .doOnError{
                Toast.makeText(context, "Проверьте сетевое подключение", Toast.LENGTH_SHORT).show()
            }
    }

    private fun launchInsertion(newsTitles:List<NewsTitle>){
        Completable.fromAction{ newsDao.insert(newsTitles) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}