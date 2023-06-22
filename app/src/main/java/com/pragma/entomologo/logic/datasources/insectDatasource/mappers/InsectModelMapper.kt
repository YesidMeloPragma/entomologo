package com.pragma.entomologo.logic.datasources.insectDatasource.mappers

import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.sources.database.entities.InsectEntity

fun InsectModel.convertToInsectEntity() : InsectEntity {
    return InsectEntity(
        id = this.id,
        specieName = this.specieName,
        urlPhoto = this.urlPhoto,
        moreInformation = this.moreInformation
    )
}