package com.pragma.entomologo.sources.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InsectEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var specieName: String,
    var urlPhoto: String,
    var moreInformation: String
): BaseEntity()
