package com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.mapper

import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import com.pragma.entomologo.sources.database.entities.CounterRecordInsectEntity

fun CounterRecordInsectModel.convertToCounterRecordInsectEntity() : CounterRecordInsectEntity{
    return CounterRecordInsectEntity(
        id = this.id,
        insectId = this.insect.id ?: 0,
        geoLocationId = this.geoLocation.id ?: 0,
        comment = this.comment,
        count = this.count
    )
}