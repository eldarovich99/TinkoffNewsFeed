package com.eldarovich99.tinkoffnews.data.network

import com.eldarovich99.tinkoffnews.BuildConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TinkoffClient{

    object Instance {
        private val client = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                            else HttpLoggingInterceptor.Level.NONE))
            .build()
        private const val BASE_URL = "https://api.tinkoff.ru/"

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(TinkoffApi::class.java)
    }
}