package com.vinithius.wtest.datasource.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "codigo_postal")
data class CodigoPostalEntity(
    @PrimaryKey
    val id: Int?,
    @ColumnInfo(name = "cod_distrito")
    val codDistrito: String,
    @ColumnInfo(name = "cod_concelho")
    val codConcelho: String,
    @ColumnInfo(name = "cod_localidade")
    val codLocalidade: String,
    @ColumnInfo(name = "nome_localidade")
    val nomeLocalidade: String,
    @ColumnInfo(name = "cod_arteria")
    val codArteria: String,
    @ColumnInfo(name = "tipo_arteria")
    val tipoArteria: String,
    @ColumnInfo(name = "prep1")
    val prep1: String,
    @ColumnInfo(name = "titulo_arteria")
    val tituloArteria: String,
    @ColumnInfo(name = "prep2")
    val prep2: String,
    @ColumnInfo(name = "nome_arteria")
    val nomeArteria: String,
    @ColumnInfo(name = "local_arteria")
    val localArteria: String,
    @ColumnInfo(name = "troco")
    val troco: String,
    @ColumnInfo(name = "porta")
    val porta: String,
    @ColumnInfo(name = "cliente")
    val cliente: String,
    @ColumnInfo(name = "num_cod_postal")
    val numCodPostal: String,
    @ColumnInfo(name = "ext_cod_postal")
    val extCodPostal: String,
    @ColumnInfo(name = "desig_postal")
    val desigPostal: String
)