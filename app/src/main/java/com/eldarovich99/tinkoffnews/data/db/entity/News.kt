package com.eldarovich99.tinkoffnews.data.db.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.eldarovich99.tinkoffnews.data.db.entity.embedded.Payload

@Entity
data class News(@Embedded var payload: Payload, var resultCode: String, var trackingId: String)