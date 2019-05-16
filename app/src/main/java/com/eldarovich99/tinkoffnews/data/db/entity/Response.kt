package com.eldarovich99.tinkoffnews.data.db.entity

data class Response(var payload: List<News>, var resultCode: String, var trackingId: String)