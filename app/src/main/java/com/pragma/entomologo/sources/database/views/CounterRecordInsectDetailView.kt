package com.pragma.entomologo.sources.database.views

import androidx.room.DatabaseView

@DatabaseView(
    "SELECT " +
    "ri.id, ri.comment, ri.count, ri.insectId, ie.specieName, ie.urlPhoto, ri.geoLocationId, ge.lat, ge.lng " +
    "FROM CounterRecordInsectEntity ri, InsectEntity ie, geolocationentity ge " +
    "WHERE " +
    "ri.insectId = ie.id and " +
    "ri.geoLocationId = ge.id"
)
data class CounterRecordInsectDetailView(
    val id: Long,
    val comment: String,
    val count: Int,
    val insectId: Long,
    val specieName: String,
    val urlPhoto: String,
    val geoLocationId: Long,
    val lat: Double,
    val lng: Double
)
