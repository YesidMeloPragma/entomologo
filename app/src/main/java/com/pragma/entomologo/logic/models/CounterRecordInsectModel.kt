package com.pragma.entomologo.logic.models

import java.util.Date

data class CounterRecordInsectModel(
    var id: Long? = null,
    var insect: InsectModel,
    var geoLocation: GeoLocationModel,
    var comment: String,
    var count: Int,
    var imageBase64: String? = null,
    var date: Date = Date()
)
