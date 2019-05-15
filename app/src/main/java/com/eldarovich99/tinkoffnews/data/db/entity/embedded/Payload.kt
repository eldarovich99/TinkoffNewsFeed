package com.eldarovich99.tinkoffnews.data.db.entity.embedded

import android.arch.persistence.room.Embedded

data class Payload(var lastModificationDate: String,
                   var typeId: String,
                   @Embedded var title: Title,
                   var creationDate: String,
                   var content: String,
                   var bankInfoTypeIdPayload:String)