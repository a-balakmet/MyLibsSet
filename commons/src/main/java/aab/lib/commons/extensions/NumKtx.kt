package aab.lib.commons.extensions

infix fun Int.intDividing(i: Int): Double = this / i.toDouble()

fun Int.twoDigitsString() = if (this < 10) "0${this}" else "$this"