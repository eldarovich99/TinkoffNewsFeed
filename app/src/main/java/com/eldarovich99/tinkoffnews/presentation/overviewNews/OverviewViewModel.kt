package com.eldarovich99.tinkoffnews.presentation.overviewNews

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.eldarovich99.tinkoffnews.data.db.entity.News
import com.eldarovich99.tinkoffnews.data.db.entity.Response
import com.eldarovich99.tinkoffnews.data.db.repository.NewsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OverviewViewModel @Inject constructor(application: Application, val repository: NewsRepository):
    AndroidViewModel(application) {
    val compositeDisposable= CompositeDisposable()
    lateinit var news: News

    fun getContent(id:String) : Observable<Response>{
        return repository.getContent(getApplication(), id)
            .subscribeOn(Schedulers.io())
            .doOnNext{response -> news = response.payload[0]}
            .observeOn(AndroidSchedulers.mainThread())
//            .doOnNext{response -> news = response.payload[0]}
//            .subscribe()
//        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
    //    private var newsLiveData = MutableLiveData<News>()
//
//    fun setNews(news: News){
//        this.newsLiveData.postValue(news)
//    }
//    fun getNews():MutableLiveData<News>{
//        return newsLiveData
//    }

//    val getNewsName = Transformations.map(newsLiveData){
//        it.name
//    }
}