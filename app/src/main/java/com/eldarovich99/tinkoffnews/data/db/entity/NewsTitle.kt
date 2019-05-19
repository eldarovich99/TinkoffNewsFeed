package com.eldarovich99.tinkoffnews.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class NewsTitle(var  name:String,
                     @PrimaryKey var id: Int,
                     var text: String,
                     var publicationDate: Long,
                     var bankInfoTypeId: Int
//                @Embedded  var lastModificationDate: PublicationDate,
//                @Embedded  var creationDate: PublicationDate
)