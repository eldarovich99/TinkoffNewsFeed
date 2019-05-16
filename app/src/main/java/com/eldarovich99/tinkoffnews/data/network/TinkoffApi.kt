package com.eldarovich99.tinkoffnews.data.network

import com.eldarovich99.tinkoffnews.data.db.entity.News
import io.reactivex.Observable
import retrofit2.http.GET
//
//private const val BASE_URL = "https://api.tinkoff.ru/"
//
//private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
//private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).build()

interface TinkoffApi{
    @GET("v1/news")
    fun getNews(): Observable<List<News>>
}

//object TinkoffApi{
//    val tinkoffService: TinkoffService by lazy { retrofit.create(TinkoffService::class.java) }
//}