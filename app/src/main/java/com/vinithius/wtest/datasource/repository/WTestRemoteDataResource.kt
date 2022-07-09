package com.vinithius.wtest.datasource.repository

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming


interface WTestRemoteDataResource {

    @Streaming
    @GET("codigos_postais.csv")
    suspend fun getCsv(): ResponseBody

}