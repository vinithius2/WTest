package com.vinithius.wtest.extension

fun String.verifyIsEmptyAndReturnValue(): Int? {
    return if (this.isNotEmpty()) {
        this.toInt()
    } else {
        null
    }
}

fun String.makeQuery(): String {
    var datas =
        this.trim().split(" ", "-", "_", "@", "'", "[", "]", ".", ",", "!", "?", "*", "|", "+")
    datas = datas.filter { it.isNotEmpty() }
    return datas.joinToString(separator = " OR ") { "*$it*" }
}

fun String.replaceAccents(): String {
    return this.lowercase().replace("[aáàäâã]".toRegex(), "a")
        .replace("[eéèëê]".toRegex(), "e")
        .replace("[iíìî]".toRegex(), "i")
        .replace("[oóòöôõ]".toRegex(), "o")
        .replace("[uúùüû]".toRegex(), "u")
}