package com.eldarovich99.tinkoffnews.data.db.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class FullNews(var lastModificationDate: Long,
                    @Embedded var title: NewsTitle,
                    var creationDate: Long,
                    var content: String,
                    var infoTypeId: Int,
                    var typeId:String,
                    @PrimaryKey var contentId:Int
)