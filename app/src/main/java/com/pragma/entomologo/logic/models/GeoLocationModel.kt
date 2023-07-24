package com.pragma.entomologo.logic.models

data class GeoLocationModel(
    var id: Long? = null,
    var lat: Double,
    var lng: Double,
    var city: String
)
