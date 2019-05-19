package com.eldarovich99.tinkoffnews.presentation.overviewNews

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.widget.Toast
import com.eldarovich99.tinkoffnews.data.db.entity.ContentResponse
import com.eldarovich99.tinkoffnews.data.db.entity.FullNews
import com.eldarovich99.tinkoffnews.data.db.repository.NewsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OverviewViewModel @Inject constructor(application: Application, val repository: NewsRepository):
    AndroidViewModel(application) {
    lateinit var fullNews: FullNews

    fun getContent(id:Int) : Observable<ContentResponse>{
        return repository.getContent(id)
            .subscribeOn(Schedulers.io())
            .doOnError{
                Toast.makeText(getApplication(), "Проверьте сетевое подключение", Toast.LENGTH_SHORT).show()
            }
            .doOnNext{response -> fullNews = response.payload}
            .observeOn(AndroidSchedulers.mainThread())
    }
}