package com.vinithius.wtest.utils

import com.vinithius.wtest.datasource.models.CodigoPostalEntity
import com.vinithius.wtest.datasource.models.CodigoPostalData

interface EntityMapper {

    fun mapFromEntity(entity: CodigoPostalEntity) : CodigoPostalData

    fun mapToEntity(response: CodigoPostalData) : CodigoPostalEntity

}