package com.eldarovich99.tinkoffnews.presentation.newsfeed

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.eldarovich99.tinkoffnews.data.db.dao.NewsDao
import com.eldarovich99.tinkoffnews.data.db.entity.News
import com.eldarovich99.tinkoffnews.data.db.repository.NewsRepository
import javax.inject.Inject

class NewsViewModel @Inject constructor(application: Application,
                                           private var newsDao: NewsDao,
                                           private var newsRepository: NewsRepository):
ViewModel() { //If you need the application context, use AndroidViewModel.
    lateinit var allNews: LiveData<List<News>>

}