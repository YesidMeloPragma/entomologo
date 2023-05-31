package com.pragma.entomologo.logic.datasources.entomologistDatasource.mapper

import com.pragma.entomologo.logic.models.EntomologistModel
import com.pragma.entomologo.sources.database.entities.EntomologistEntity

fun EntomologistEntity.convertToEntomologistModel() : EntomologistModel {
    return EntomologistModel(
        id = this.id,
        name = this.name,
        urlPhoto = this.urlPhoto
    )
}

fun List<EntomologistEntity>.convertToListEntomologistModel() : List<EntomologistModel> {
    return this.map { it.convertToEntomologistModel()  }
}