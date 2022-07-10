package com.vinithius.wtest.datasource.models

data class LoadingData(
    val isProgress: Boolean = false,
    val statusText: String = "",
    val count: Int = 0,
    val size: Int = 0
)
