package com.eldarovich99.tinkoffnews.data.network

import com.eldarovich99.tinkoffnews.data.db.entity.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.tinkoff.ru/"

private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create()).build()

interface TinkoffService{
    @GET("v1/news")
    fun getNews(): Call<News>
}

object TinkoffApi{
    val tinkoffService: TinkoffService by lazy { retrofit.create(TinkoffService::class.java) }
}