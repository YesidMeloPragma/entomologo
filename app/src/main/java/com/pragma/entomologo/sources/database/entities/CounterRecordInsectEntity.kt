package com.pragma.entomologo.sources.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = GeoLocationEntity::class,
            parentColumns = ["id"],
            childColumns = ["geoLocationId"]
        ),
        ForeignKey(
            entity = InsectEntity::class,
            parentColumns = ["id"],
            childColumns = ["insectId"]
        ),
    ]
)
data class CounterRecordInsectEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    var id: Long?,
    @ColumnInfo(index = true)
    var insectId: Long,
    @ColumnInfo(index = true)
    var geoLocationId: Long,
    var comment: String,
    var count: Int,
    var date: Date
) : BaseEntity()
