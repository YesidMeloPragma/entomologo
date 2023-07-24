package com.pragma.entomologo.sources.gpsLocation

interface GPSLocation {
    suspend fun iHaveGPSPermissions(): Boolean
    suspend fun getLocation(): Pair<Pair<Double, Double>, String>
}