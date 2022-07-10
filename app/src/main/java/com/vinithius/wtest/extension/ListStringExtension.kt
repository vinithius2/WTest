package com.vinithius.wtest.extension

import com.vinithius.wtest.datasource.models.CodigoPostalEntity

fun Array<String>.getEntityByArray(): CodigoPostalEntity? {
    return try {
        CodigoPostalEntity(
            id = null,
            codDistrito = this[0],
            codConcelho = this[1],
            codLocalidade = this[2],
            nomeLocalidade = this[3],
            codArteria = this[4],
            tipoArteria = this[5],
            prep1 = this[6],
            tituloArteria = this[7],
            prep2 = this[8],
            nomeArteria = this[9],
            localArteria = this[10],
            troco = this[11],
            porta = this[12],
            cliente = this[13],
            numCodPostal = this[14],
            extCodPostal = this[15],
            desigPostal = this[16]
        )
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}