package com.mydigipay.challenge.utils.ktx

import java.util.*

private val suffixes: NavigableMap<Long, String> = TreeMap<Long, String>().apply {
    put(1_000L, "k")
    put(1_000_000L, "M")
    put(1_000_000_000L, "G")
    put(1_000_000_000_000L, "T")
    put(1_000_000_000_000_000L, "P")
    put(1_000_000_000_000_000_000L, "E")
}

fun Long.toHumanReadableFormat(): String {
    //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
    if (this == Long.MIN_VALUE) return (Long.MIN_VALUE + 1).toHumanReadableFormat()
    if (this < 0) return "-" + (-this).toHumanReadableFormat()
    if (this < 1000) return this.toString()
    val e: Map.Entry<Long, String> = suffixes.floorEntry(this) ?: return ""
    val divideBy = e.key
    val suffix = e.value
    val truncated = this / (divideBy / 10) //the number part of the output times 10
    val hasDecimal =
        truncated < 100 && truncated / 10.0 != (truncated / 10).toDouble()
    return if (hasDecimal) (truncated / 10.0).toString() + suffix else (truncated / 10).toString() + suffix
}

fun Int.toHumanReadableFormat() = this.toLong().toHumanReadableFormat()