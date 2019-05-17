package com.eldarovich99.tinkoffnews.presentation.newsfeed

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.eldarovich99.tinkoffnews.data.db.entity.News
import com.eldarovich99.tinkoffnews.data.network.TinkoffClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsViewModel:
ViewModel() { //If you need the application context, use AndroidViewModel.
    var allNews = MutableLiveData<List<News>>()
    private lateinit var compositeDisposable: CompositeDisposable

    init {
        getNews()
    }

    private fun getNews(){
        val api = TinkoffClient.Instance.api
        val disposable = api.getResponse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                val data = response.payload.toMutableList()
                data.sortByDescending { it.publicationDate.milliseconds }
                 data.toList()
            }
            .subscribe{ news -> allNews.postValue(news)}
        compositeDisposable = CompositeDisposable(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun cacheNews(news:List<News>){
    }
}