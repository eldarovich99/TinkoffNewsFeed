package com.eldarovich99.tinkoffnews.presentation.newsfeed

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.eldarovich99.tinkoffnews.data.db.dao.NewsDao
import com.eldarovich99.tinkoffnews.data.db.entity.News
import com.eldarovich99.tinkoffnews.data.db.repository.NewsRepository
import com.eldarovich99.tinkoffnews.data.network.TinkoffApi
import com.eldarovich99.tinkoffnews.data.network.TinkoffClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsViewModel @Inject constructor(application: Application,
                                           private var newsDao: NewsDao,
                                           private var newsRepository: NewsRepository):
ViewModel() { //If you need the application context, use AndroidViewModel.
    lateinit var allNews: LiveData<List<News>>
    private lateinit var compositeDisposable: CompositeDisposable

    init {
        getNews()
    }

    private fun getNews(){
        val api = TinkoffClient.Instance.api
        val disposable = api.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ news -> cacheNews(news)}
        compositeDisposable.add(disposable)
//        TinkoffApi.tinkoffService.getNews().ob
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun cacheNews(news:List<News>){
        Log.d("news", "looks cached")
    }
}