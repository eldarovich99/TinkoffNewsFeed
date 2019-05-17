package com.eldarovich99.tinkoffnews.data.db.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class News(var  name:String,
                @PrimaryKey var id: String,
                var text: String,
                @Embedded  var publicationDate: PublicationDate,
                var bankInfoTypeId: String
):Parcelable