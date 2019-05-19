package com.eldarovich99.tinkoffnews.data.network

import com.eldarovich99.tinkoffnews.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


class TinkoffClient{

    object Instance {
        private val client = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                            else HttpLoggingInterceptor.Level.NONE))
            .build()
        private const val BASE_URL = "https://api.tinkoff.ru/"

        private fun createGsonConverter(type: Type, typeAdapter: Any): Converter.Factory {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(type, typeAdapter)
            val gson = gsonBuilder.create()

            return GsonConverterFactory.create(gson)
        }

        fun getRetrofitInstance(type: Type, typeAdapter: Any): Retrofit {
            return retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(createGsonConverter(type, typeAdapter))
                .build()
        }
    }
}