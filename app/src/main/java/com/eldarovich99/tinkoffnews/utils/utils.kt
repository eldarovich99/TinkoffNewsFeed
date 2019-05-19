package com.eldarovich99.tinkoffnews.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDateString(): String{
    /**
     *
     * Converts Long in milliseconds to date with pattern dd.mm.yyyy
     * @return formatted string which represents data
     *
     */
    return SimpleDateFormat("dd.mm.yyyy", Locale("ru"))
        .format(Date(this))
        .toString()
}
fun Long.toDateTimeString(): String{
    /**
     *
     * Converts Long in milliseconds to date with pattern dd.mm.yyyy   hh:mm:ss
     * @return formatted string which represents data
     *
     */
    return SimpleDateFormat("dd.mm.yyyy   hh:mm:ss", Locale("ru"))
        .format(Date(this))
        .toString()
}