package com.pragma.entomologo.logic.datasources.geoLocationDatasource.localDatasource

import com.pragma.entomologo.logic.models.GeoLocationModel
import kotlinx.coroutines.flow.Flow

interface GeoLocationLocalDatasource {
    fun deleteGeoLocation(geoLocationModel: GeoLocationModel) : Flow<Boolean?>
    fun getAllGeoLocations(): Flow<List<GeoLocationModel>>
    suspend fun insertGeoLocation(geoLocationModel: GeoLocationModel) : LongArray
    fun updateGeoLocation(geoLocationModel: GeoLocationModel) : Flow<Boolean>
}