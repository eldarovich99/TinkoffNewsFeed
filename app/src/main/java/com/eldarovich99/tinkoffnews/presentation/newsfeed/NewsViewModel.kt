package com.eldarovich99.tinkoffnews.presentation.newsfeed

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.eldarovich99.tinkoffnews.data.db.entity.NewsTitle
import com.eldarovich99.tinkoffnews.data.db.repository.NewsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsViewModel @Inject constructor(application: Application, var newsRepository: NewsRepository):
AndroidViewModel(application) {
    var allNews = mutableListOf<NewsTitle>()

    fun getNewsList() : Observable<List<NewsTitle>> {
        return newsRepository
            .getNewsList(getApplication())
            .doOnNext{
                    news -> val mutable = news.toMutableList()
                mutable.sortByDescending { it.publicationDate }
                allNews.addAll(news)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}