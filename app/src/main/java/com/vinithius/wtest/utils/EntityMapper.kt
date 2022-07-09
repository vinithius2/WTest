package com.vinithius.wtest.utils

import com.vinithius.wtest.datasource.models.CodigoPostalEntity
import com.vinithius.wtest.datasource.models.CodigoPostalResponse

interface EntityMapper {

    fun mapFromEntity(entity: CodigoPostalEntity) : CodigoPostalResponse

    fun mapToEntity(response: CodigoPostalResponse) : CodigoPostalEntity

}