package com.vinithius.wtest.datasource.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4

@Entity(tableName = "codigo_postal_fts")
@Fts4(contentEntity = CodigoPostalEntity::class)
data class CodigoPostalFTS(
    @ColumnInfo(name = "num_cod_postal")
    val numCodPostal: String,
    @ColumnInfo(name = "ext_cod_postal")
    val extCodPostal: String,
    @ColumnInfo(name = "desig_postal_no_accents")
    val desigPostalNoAccents: String
)
