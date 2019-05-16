package com.eldarovich99.tinkoffnews.data.network

import com.eldarovich99.tinkoffnews.data.db.entity.Response
import io.reactivex.Observable
import retrofit2.http.GET

//
//private const val BASE_URL = "https://api.tinkoff.ru/"
//

//private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).build()

interface TinkoffApi{
    @GET("v1/news")
    fun getResponse(): Observable<Response>

}

//object TinkoffApi{
//    val tinkoffService: TinkoffService by lazy { retrofit.create(TinkoffService::class.java) }
//}