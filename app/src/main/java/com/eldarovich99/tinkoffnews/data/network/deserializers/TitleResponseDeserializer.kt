package com.eldarovich99.tinkoffnews.data.network.deserializers

import com.eldarovich99.tinkoffnews.data.db.entity.News
import com.eldarovich99.tinkoffnews.data.db.entity.Response
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class TitleResponseDeserializer:JsonDeserializer<Response> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Response {
        val jsonObject = json?.asJsonObject
        val payload = jsonObject?.get("payload")?.asJsonArray

        val items = mutableListOf<News>()
        for (jsonElement in payload!!){
            val item = jsonElement.asJsonObject
            val name = item.get("name").asString
            val id = item.get("id").asString.toInt()
            val text = item.get("text").asString
            val publicationDate = item.get("publicationDate").asJsonObject.get("milliseconds").asLong
            val bankInfoTypeId = item.get("bankInfoTypeId").asInt
            items.add(News(name, id, text, publicationDate, bankInfoTypeId))
        }
        val resultCode = jsonObject.get("resultCode")?.asString
        val trackingId = jsonObject.get("trackingId")?.asString
        return Response(items, resultCode!!, trackingId!!)
    }
}