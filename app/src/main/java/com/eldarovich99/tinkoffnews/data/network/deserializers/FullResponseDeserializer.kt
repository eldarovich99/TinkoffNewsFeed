package com.eldarovich99.tinkoffnews.data.network.deserializers

import com.eldarovich99.tinkoffnews.data.db.entity.ContentResponse
import com.eldarovich99.tinkoffnews.data.db.entity.FullNews
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class FullResponseDeserializer: JsonDeserializer<ContentResponse> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ContentResponse {
        val jsonObject = json?.asJsonObject
        val payload = jsonObject?.get("payload")?.asJsonObject

        val title = payload?.get("title")?.asJsonObject
        val news = TitleResponseDeserializer.deserializeTitle(title!!)

        val creationDate = payload.get("creationDate").asJsonObject.get("milliseconds").asLong
        val lastModificationDate = payload.get("lastModificationDate").asJsonObject.get("milliseconds").asLong
        val content = payload.get("content").asString
        val bankInfoTypeId = payload.get("bankInfoTypeId").asInt
        val typeId = payload.get("typeId").asString
        val resultCode = jsonObject.get("resultCode").asString
        val trackingId = jsonObject.get("trackingId").asString
        return ContentResponse(FullNews(lastModificationDate, news, creationDate, content, bankInfoTypeId, typeId, news.id),resultCode, trackingId)
    }

}