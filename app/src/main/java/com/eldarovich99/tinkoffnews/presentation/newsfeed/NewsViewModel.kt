package com.eldarovich99.tinkoffnews.presentation.newsfeed

import android.arch.lifecycle.ViewModel
import com.eldarovich99.tinkoffnews.data.db.entity.News
import com.eldarovich99.tinkoffnews.data.db.repository.NewsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsViewModel @Inject constructor(var newsRepository: NewsRepository):
ViewModel() {
    var allNews = mutableListOf<News>()

    fun getNewsList() : Observable<List<News>> {
        return newsRepository
            .getNewsList()
            .doOnNext{news -> allNews.addAll(news)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}