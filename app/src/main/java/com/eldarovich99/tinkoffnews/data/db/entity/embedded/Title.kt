package com.eldarovich99.tinkoffnews.data.db.entity.embedded

import android.arch.persistence.room.PrimaryKey

data class Title(var  name:String,
                 @PrimaryKey var id: String,
                 var text: String,
                 var publicationDate: String,
                 var bankInfoTypeId: String
)