package com.mydigipay.challenge.util

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit.*
import kotlin.math.abs

fun Long.toRelativeTime(): String {
    val times: List<Long> = listOf(
        DAYS.toMillis(365),
        DAYS.toMillis(30),
        DAYS.toMillis(7),
        DAYS.toMillis(1),
        HOURS.toMillis(1),
        MINUTES.toMillis(1),
        SECONDS.toMillis(1)
    )

    val timesString: List<String> = listOf(
        "year",
        "month",
        "week",
        "day",
        "hour",
        "minute",
        "second"
    )

    val diff = System.currentTimeMillis() - this
    val sb = StringBuilder()
    for (i in times.indices) {
        val current = times[i]
        val temp = abs(diff) / current
        if (temp > 0) {
            sb.append(temp)
                .append(" ")
                .append(timesString[i])
                .append(if (temp > 1) "s" else "")
                .append(if (diff >= 0) " ago" else " from now")
            break
        }
    }
    return if (sb.toString().isEmpty()) "now" else sb.toString()
}

fun String.toMillis(pattern: String = "yyyy-MM-dd'T'HH:mm:ss") =
    SimpleDateFormat(pattern, Locale.ENGLISH).parse(this)?.time