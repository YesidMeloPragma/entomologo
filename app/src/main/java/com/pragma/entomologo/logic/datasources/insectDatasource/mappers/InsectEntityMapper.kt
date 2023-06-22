package com.pragma.entomologo.logic.datasources.insectDatasource.mappers

import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.sources.database.entities.InsectEntity

fun InsectEntity.convertToInsectModel() : InsectModel {
    return InsectModel(
        id = this.id,
        specieName = this.specieName,
        urlPhoto = this.urlPhoto,
        moreInformation = this.moreInformation
    )
}

fun List<InsectEntity>.convertToListInsectModel() : List<InsectModel> {
    return this.map { it.convertToInsectModel() }
}