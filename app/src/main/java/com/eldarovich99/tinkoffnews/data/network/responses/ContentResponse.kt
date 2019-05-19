package com.eldarovich99.tinkoffnews.data.network.responses

import com.eldarovich99.tinkoffnews.data.db.entity.FullNews

data class ContentResponse(val payload: FullNews, val resultCode: String, val trackingId: String)