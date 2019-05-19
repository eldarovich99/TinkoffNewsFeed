package com.eldarovich99.tinkoffnews.data.network

import com.eldarovich99.tinkoffnews.data.network.responses.ContentResponse
import com.eldarovich99.tinkoffnews.data.network.responses.TitleResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TinkoffApi{
    @GET("v1/news")
    fun getNews(): Observable<TitleResponse>
    @GET("v1/news_content")
    fun getContent(@Query("id")id:Int): Observable<ContentResponse>
}