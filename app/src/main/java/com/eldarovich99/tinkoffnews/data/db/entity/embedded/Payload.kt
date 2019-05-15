package com.eldarovich99.tinkoffnews.data.db.entity.embedded

data class Payload( var lastModificationDate: String,
                    var typeId: String,
                    var title: Title,
                    var creationDate: String,
                    var content: String,
                    var bankInfoTypeId:String)