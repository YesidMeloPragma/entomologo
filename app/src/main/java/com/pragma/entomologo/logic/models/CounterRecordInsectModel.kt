package com.pragma.entomologo.logic.models

data class CounterRecordInsectModel(
    var id: Long? = null,
    var insect: InsectModel,
    var geoLocation: GeoLocationModel,
    var comment: String,
    var count: Int,
)
