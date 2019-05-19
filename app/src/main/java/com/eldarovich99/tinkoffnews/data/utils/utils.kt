package com.eldarovich99.tinkoffnews.data.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDateString(): String{
    return SimpleDateFormat("dd.mm.yyyy", Locale("ru"))
        .format(Date(this))
        .toString()
}
fun Long.toDateTimeString(): String{
    return SimpleDateFormat("dd.mm.yyyy   hh:mm:ss", Locale("ru"))
        .format(Date(this))
        .toString()
}