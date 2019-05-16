package com.eldarovich99.tinkoffnews.data.db.entity

import com.eldarovich99.tinkoffnews.data.db.entity.News

data class Response(var payload: List<News>, var resultCode: String, var trackingId: String)