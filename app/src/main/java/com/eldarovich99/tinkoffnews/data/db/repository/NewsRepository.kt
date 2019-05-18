package com.eldarovich99.tinkoffnews.data.db.repository

import android.content.Context
import android.support.annotation.WorkerThread
import android.widget.Toast
import com.eldarovich99.tinkoffnews.data.db.dao.NewsDao
import com.eldarovich99.tinkoffnews.data.db.entity.News
import com.eldarovich99.tinkoffnews.data.db.entity.Response
import com.eldarovich99.tinkoffnews.data.network.TinkoffClient
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsDao: NewsDao) {
    val api = TinkoffClient.Instance.api
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
        return newsDao.getAllNews()
    }
    @WorkerThread
    fun getContentFromDB(id:String) : Flowable<News> {
        return newsDao.getContent(id)
    }

    fun getNewsList(context: Context): Observable<List<News>>{
        return api.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                val data = response.payload.toMutableList()
                data.sortByDescending { it.publicationDate.milliseconds }
                val list = data.toList()
                launchInsertion(list)
                list
            }
            .doOnError{
                Toast.makeText(context, "Проверьте сетевое подключение", Toast.LENGTH_SHORT).show()
            }
            .onErrorResumeNext(getNews().toObservable())
    }

    fun getContent(context: Context, id: String) : Observable<Response>{
        return api.getContent(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext{news -> launchInsertion(news.payload)}
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