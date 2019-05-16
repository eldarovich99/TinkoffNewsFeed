package com.eldarovich99.tinkoffnews.presentation.newsfeed

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.eldarovich99.tinkoffnews.data.db.dao.NewsDao
import com.eldarovich99.tinkoffnews.data.db.entity.News
import com.eldarovich99.tinkoffnews.data.db.repository.NewsRepository
import com.eldarovich99.tinkoffnews.data.network.TinkoffApi
import javax.inject.Inject

class NewsViewModel @Inject constructor(application: Application,
                                           private var newsDao: NewsDao,
                                           private var newsRepository: NewsRepository):
ViewModel() { //If you need the application context, use AndroidViewModel.
    lateinit var allNews: LiveData<List<News>>

    private fun getNews(){
        TinkoffApi.tinkoffService.getNews().enqueue( object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }
        })
    }
}