package com.eldarovich99.tinkoffnews.data.network

import com.eldarovich99.tinkoffnews.data.db.entity.News
import com.eldarovich99.tinkoffnews.data.db.entity.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

//
//private const val BASE_URL = "https://api.tinkoff.ru/"
//

//private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).build()

interface TinkoffApi{
    @GET("v1/news")
    fun getNews(): Observable<Response>

//    @GET("v1/news")
//    fun getPayload(@Query("payload")) :
}

//object TinkoffApi{
//    val tinkoffService: TinkoffService by lazy { retrofit.create(TinkoffService::class.java) }
//}