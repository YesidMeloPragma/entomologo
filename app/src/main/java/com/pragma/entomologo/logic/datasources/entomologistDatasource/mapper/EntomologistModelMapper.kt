package com.pragma.entomologo.logic.datasources.entomologistDatasource.mapper

import com.pragma.entomologo.logic.models.EntomologistModel
import com.pragma.entomologo.sources.database.entities.EntomologistEntity

fun EntomologistModel.convertToEntomologistEntity(): EntomologistEntity {
    return EntomologistEntity(
        id = this.id,
        name = this.name,
        urlPhoto = this.urlPhoto
    )
}