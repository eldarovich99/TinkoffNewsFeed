package com.eldarovich99.tinkoffnews.data.network.deserializers

import com.eldarovich99.tinkoffnews.data.db.entity.NewsTitle
import com.eldarovich99.tinkoffnews.data.network.responses.TitleResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class TitleResponseDeserializer:JsonDeserializer<TitleResponse> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): TitleResponse {
        val jsonObject = json?.asJsonObject
        val payload = jsonObject?.get("payload")?.asJsonArray

        val items = mutableListOf<NewsTitle>()
        for (jsonElement in payload!!){
            items.add(deserializeTitle(jsonElement))
        }
        val resultCode = jsonObject.get("resultCode")?.asString
        val trackingId = jsonObject.get("trackingId")?.asString
        return TitleResponse(items, resultCode!!, trackingId!!)
    }

    companion object{
        fun deserializeTitle(json: JsonElement): NewsTitle{
            val item = json.asJsonObject
            val name = item.get("name").asString
            val id = item.get("id").asString.toInt()
            val text = item.get("text").asString
            val publicationDate = item.get("publicationDate").asJsonObject.get("milliseconds").asLong
            val bankInfoTypeId = item.get("bankInfoTypeId").asInt
            return NewsTitle(name, id, text, publicationDate, bankInfoTypeId)
        }
    }
}