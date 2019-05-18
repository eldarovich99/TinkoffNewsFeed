package com.eldarovich99.tinkoffnews.data.network

import com.eldarovich99.tinkoffnews.data.db.entity.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TinkoffApi{
    @GET("v1/news")
    fun getNews(): Observable<Response>
    @GET("v1/news_content")
    fun getContent(@Query("id")id:String): Observable<Response>
}