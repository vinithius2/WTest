package com.vinithius.wtest.datasource.models

import retrofit2.http.Field

data class CodigoPostalResponse(
    @Field("cod_distrito")
    val codDistrito: String,
    @Field("cod_concelho")
    val codConcelho: String,
    @Field("cod_localidade")
    val codLocalidade: String,
    @Field("nome_localidade")
    val nomeLocalidade: String,
    @Field("cod_arteria")
    val codArteria: String,
    @Field("tipo_arteria")
    val tipoArteria: String,
    val prep1: String,
    @Field("titulo_arteria")
    val tituloArteria: String,
    val prep2: String,
    @Field("nome_arteria")
    val nomeArteria: String,
    @Field("local_arteria")
    val localArteria: String,
    val troco: String,
    val porta: String,
    val cliente: String,
    @Field("num_cod_postal")
    val numCodPostal: String,
    @Field("ext_cod_postal")
    val extCodPostal: String,
    @Field("desig_postal")
    val desigPostal: String
)