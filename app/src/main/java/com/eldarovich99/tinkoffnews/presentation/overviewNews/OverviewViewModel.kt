package com.eldarovich99.tinkoffnews.presentation.overviewNews

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.widget.Toast
import com.eldarovich99.tinkoffnews.R
import com.eldarovich99.tinkoffnews.data.db.entity.FullNews
import com.eldarovich99.tinkoffnews.data.db.repository.NewsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OverviewViewModel @Inject constructor(application: Application, val repository: NewsRepository):
    AndroidViewModel(application) {
    lateinit var fullNews: FullNews

    fun getContent(id:Int) : Observable<FullNews>{
        return repository.getContent(id)
            .doOnNext{response -> fullNews = response}
            .doOnError{
                Toast.makeText(getApplication(), getApplication<Application>().resources.getString(R.string.check_connection), Toast.LENGTH_SHORT).show()
            }
            .onErrorResumeNext(repository.getContentFromDB(id).toObservable())
            .doOnNext{response -> fullNews = response}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}