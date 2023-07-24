package com.pragma.entomologo.logic.datasources.geoLocationDatasource.gpsDatasource

interface GPSDatasource {
    suspend fun getLocation(): Pair<Pair<Double, Double>, String>
    suspend fun iHaveGPSPermissions(): Boolean
}