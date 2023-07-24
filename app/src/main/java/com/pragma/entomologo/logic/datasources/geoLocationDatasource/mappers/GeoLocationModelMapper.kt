package com.pragma.entomologo.logic.datasources.geoLocationDatasource.mappers

import com.pragma.entomologo.logic.models.GeoLocationModel
import com.pragma.entomologo.sources.database.entities.GeoLocationEntity

fun GeoLocationModel.convertToGeoLocationEntity() : GeoLocationEntity {
    return GeoLocationEntity(
        id = this.id,
        lat = this.lat,
        lng = this.lng,
        city = this.city
    )
}