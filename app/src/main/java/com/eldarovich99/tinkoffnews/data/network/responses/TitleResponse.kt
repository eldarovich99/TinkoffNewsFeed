package com.eldarovich99.tinkoffnews.data.network.responses

import com.eldarovich99.tinkoffnews.data.db.entity.NewsTitle

data class TitleResponse(val payload: List<NewsTitle>, val resultCode: String, val trackingId: String)