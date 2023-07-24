package com.pragma.entomologo.logic.datasources.geoLocationDatasource.gpsDatasource

import com.pragma.entomologo.sources.gpsLocation.GPSLocation
import javax.inject.Inject

class GPSDatasourceImpl @Inject constructor(
    private val gpsLocation: GPSLocation
) : GPSDatasource {

    override suspend fun getLocation(): Pair<Pair<Double, Double>, String>
        = gpsLocation.getLocation()

    override suspend fun iHaveGPSPermissions(): Boolean
        = gpsLocation.iHaveGPSPermissions()

}