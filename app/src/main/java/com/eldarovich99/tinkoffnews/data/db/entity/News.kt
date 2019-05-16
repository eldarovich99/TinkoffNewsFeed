package com.eldarovich99.tinkoffnews.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class News(var  name:String,
                 @PrimaryKey var id: String,
                 var text: String,
                 var publicationDate: String,
                 var bankInfoTypeId: String
)