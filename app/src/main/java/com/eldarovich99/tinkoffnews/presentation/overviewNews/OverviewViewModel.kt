package com.eldarovich99.tinkoffnews.presentation.overviewNews

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.eldarovich99.tinkoffnews.data.db.entity.ContentResponse
import com.eldarovich99.tinkoffnews.data.db.entity.NewsTitle
import com.eldarovich99.tinkoffnews.data.db.repository.NewsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OverviewViewModel @Inject constructor(application: Application, val repository: NewsRepository):
    AndroidViewModel(application) {
    val compositeDisposable= CompositeDisposable()
    lateinit var newsTitle: NewsTitle

    fun getContent(id:Int) : Observable<ContentResponse>{
        return repository.getContent(getApplication(), id)
            .subscribeOn(Schedulers.io())
            .doOnNext{response -> newsTitle = response.payload.title}
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