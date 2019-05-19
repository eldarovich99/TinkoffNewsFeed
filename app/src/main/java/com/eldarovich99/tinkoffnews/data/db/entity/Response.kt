package com.eldarovich99.tinkoffnews.data.db.entity

data class Response(val payload: List<News>, val resultCode: String, val trackingId: String)