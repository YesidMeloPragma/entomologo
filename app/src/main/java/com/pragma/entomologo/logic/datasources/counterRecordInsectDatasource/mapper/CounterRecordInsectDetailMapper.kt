package com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.mapper

import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import com.pragma.entomologo.logic.models.GeoLocationModel
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.sources.database.views.CounterRecordInsectDetailView

fun CounterRecordInsectDetailView.toCounterRecordInsectModel() : CounterRecordInsectModel {
    return CounterRecordInsectModel(
        id = this.id,
        date = this.date,
        insect = InsectModel(
            id= insectId,
            specieName = specieName,
            moreInformation = "",
            urlPhoto = urlPhoto
        ),
        geoLocation = GeoLocationModel(
            id = geoLocationId,
            lat = lat,
            lng = lng,
            city = city
        ),
        comment = comment,
        count = count
    )
}

fun List<CounterRecordInsectDetailView>.toListCounterRecordInsectModel() : List<CounterRecordInsectModel> {
    return map { it.toCounterRecordInsectModel() }
}