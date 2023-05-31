package com.pragma.entomologo.sources.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GeoLocationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    var id: Long?,
    var lat: Double,
    var lng: Double
): BaseEntity()