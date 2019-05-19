package com.eldarovich99.tinkoffnews.data.db.entity

data class FullNews(var lastModificationDate: Long,
                    var title: NewsTitle,
                    var creationDate: Long,
                    var content: String,
                    var bankInfoTypeId: Int,
                    var typeId:String
)