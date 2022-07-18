package com.vinithius.wtest.datasource.models

import com.vinithius.wtest.utils.EntityMapper

class Mapper : EntityMapper {

    override fun mapFromEntity(entity: CodigoPostalEntity): CodigoPostalData {
        return CodigoPostalData(
            codDistrito = entity.codDistrito,
            codConcelho = entity.codConcelho,
            codLocalidade = entity.codLocalidade,
            nomeLocalidade = entity.codLocalidade,
            codArteria = entity.codArteria,
            tipoArteria = entity.tipoArteria,
            prep1 = entity.prep1,
            tituloArteria = entity.tituloArteria,
            prep2 = entity.prep2,
            nomeArteria = entity.nomeArteria,
            localArteria = entity.localArteria,
            troco = entity.troco,
            porta = entity.porta,
            cliente = entity.cliente,
            numCodPostal = entity.numCodPostal,
            extCodPostal = entity.extCodPostal,
            desigPostal = entity.desigPostal,
            desigPostalNoAccents = entity.desigPostalNoAccents
        )
    }
}
