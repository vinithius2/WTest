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
            desigPostal = entity.desigPostal
        )
    }

    override fun mapToEntity(response: CodigoPostalData): CodigoPostalEntity {
        return CodigoPostalEntity(
            id = null,
            codDistrito = response.codDistrito,
            codConcelho = response.codConcelho,
            codLocalidade = response.codLocalidade,
            nomeLocalidade = response.codLocalidade,
            codArteria = response.codArteria,
            tipoArteria = response.tipoArteria,
            prep1 = response.prep1,
            tituloArteria = response.tituloArteria,
            prep2 = response.prep2,
            nomeArteria = response.nomeArteria,
            localArteria = response.localArteria,
            troco = response.troco,
            porta = response.porta,
            cliente = response.cliente,
            numCodPostal = response.numCodPostal,
            extCodPostal = response.extCodPostal,
            desigPostal = response.desigPostal
        )
    }
}
