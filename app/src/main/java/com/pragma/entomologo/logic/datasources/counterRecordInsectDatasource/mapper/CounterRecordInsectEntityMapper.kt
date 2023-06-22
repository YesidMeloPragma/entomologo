package com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.mapper

import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import com.pragma.entomologo.logic.models.GeoLocationModel
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.sources.database.entities.CounterRecordInsectEntity

fun CounterRecordInsectEntity.convertToCounterRecordInsectModel() : CounterRecordInsectModel {
    return CounterRecordInsectModel(
        id = this.id,
        comment = this.comment,
        count = this.count,
        insect = InsectModel(id = this.insectId, specieName = "",  urlPhoto = "", moreInformation = ""),
        geoLocation = GeoLocationModel(id = this.geoLocationId, lat = 0.0, lng = 0.0)
    )
}

fun List<CounterRecordInsectEntity>.convertToListCounterRecordInsectModel() : List<CounterRecordInsectModel> {
    return this.map { return@map it.convertToCounterRecordInsectModel() }
}