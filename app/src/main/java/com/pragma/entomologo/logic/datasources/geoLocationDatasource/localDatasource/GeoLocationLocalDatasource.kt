package com.pragma.entomologo.logic.datasources.geoLocationDatasource.localDatasource

import com.pragma.entomologo.logic.models.GeoLocationModel
import kotlinx.coroutines.flow.Flow

interface GeoLocationLocalDatasource {
    fun deleteGeoLocation(geoLocationModel: GeoLocationModel) : Flow<Boolean?>
    fun getAllGeoLocations(): Flow<List<GeoLocationModel>>
    fun insertGeoLocation(geoLocationModel: GeoLocationModel) : Flow<LongArray>
    fun updateGeoLocation(geoLocationModel: GeoLocationModel) : Flow<Boolean>
}