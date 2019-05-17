package com.eldarovich99.tinkoffnews.data.network

import com.eldarovich99.tinkoffnews.data.db.entity.Response
import io.reactivex.Observable
import retrofit2.http.GET

interface TinkoffApi{
    @GET("v1/news")
    fun getResponse(): Observable<Response>

}