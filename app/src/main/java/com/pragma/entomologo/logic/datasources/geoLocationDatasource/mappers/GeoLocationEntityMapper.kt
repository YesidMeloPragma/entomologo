package com.pragma.entomologo.logic.datasources.geoLocationDatasource.mappers

import com.pragma.entomologo.logic.models.GeoLocationModel
import com.pragma.entomologo.sources.database.entities.GeoLocationEntity

fun GeoLocationEntity.convertToGeoLocationModel() : GeoLocationModel {
    return GeoLocationModel(
        id = this.id,
        lat = this.lat,
        lng = this.lng,
    )
}

fun List<GeoLocationEntity>.convertToListGeoLocationModel() : List<GeoLocationModel> {
    return this.map { it.convertToGeoLocationModel() }
}