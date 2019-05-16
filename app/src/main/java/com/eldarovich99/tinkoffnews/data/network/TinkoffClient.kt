package com.eldarovich99.tinkoffnews.data.network

import com.eldarovich99.tinkoffnews.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class TinkoffClient{
    companion object {
        private lateinit var api: TinkoffApi
    }
    object Instance {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                            else HttpLoggingInterceptor.Level.NONE))
            .build()
        private const val BASE_URL = "https://api.tinkoff.ru/"

        private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
        val api = retrofit.create(TinkoffApi::class.java)
    }
}