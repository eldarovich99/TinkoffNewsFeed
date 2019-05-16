package com.eldarovich99.tinkoffnews.data.network

import android.util.Log
import com.eldarovich99.tinkoffnews.BuildConfig
import com.eldarovich99.tinkoffnews.data.db.entity.News
import com.eldarovich99.tinkoffnews.data.db.entity.Response
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class TinkoffClient{
    //lateinit var api: TinkoffApi

    object Instance {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                            else HttpLoggingInterceptor.Level.NONE))
            .build()
        private const val BASE_URL = "https://api.tinkoff.ru/"

        val gsonBuilder = GsonBuilder().registerTypeAdapter(Response::class.java, JsonDeserializer{
            arg0: JsonElement, arg1: Type, arg2:JsonDeserializationContext ->
            {
                //val response: Response = arg2.deserialize(arg0, object: TypeToken<List<News>>(){}.type)
                var news = mutableListOf<News>()
                Log.d("news", "1")
                if (arg0.asJsonObject.get("payload").isJsonArray){
                    news = arg2.deserialize(arg0.asJsonObject.get("payload"),
                        object: TypeToken<List<News>>(){}.type)
                    Log.d("news", "2")
                }else {
                    val single: News = arg2.deserialize(arg0.asJsonObject.get("payload"), News::class.java)
                    news.add(single)
                    Log.d("news", "3")
                }
                news
//                response.payload = news
//                response
            }
        }

        )
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(TinkoffApi::class.java)
    }
}