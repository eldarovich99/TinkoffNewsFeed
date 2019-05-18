package com.eldarovich99.tinkoffnews.presentation.newsfeed

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.eldarovich99.tinkoffnews.data.db.entity.News
import com.eldarovich99.tinkoffnews.data.db.repository.NewsRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsViewModel @Inject constructor(var newsRepository: NewsRepository):
ViewModel() {
    var allNews = MutableLiveData<List<News>>()
    private lateinit var compositeDisposable: CompositeDisposable

    init {
        getNewsList()
    }

    fun getNewsList(){
        val disposable = newsRepository.getNewsList().subscribe{news -> allNews.postValue(news)}
        compositeDisposable = CompositeDisposable(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun cacheNews(news:List<News>){
    }
}