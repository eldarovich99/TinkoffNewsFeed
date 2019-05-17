package com.eldarovich99.tinkoffnews.presentation.overviewNews

import android.arch.lifecycle.ViewModel
import com.eldarovich99.tinkoffnews.data.db.entity.News

class OverviewViewModel: ViewModel() {
    lateinit var news: News
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