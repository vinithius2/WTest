package com.vinithius.wtest.extension

fun String.verifyIsEmptyAndReturnValue(): Int? {
    return if (this.isNotEmpty()) {
        this.toInt()
    } else {
        null
    }
}