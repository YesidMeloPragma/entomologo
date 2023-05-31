package com.pragma.entomologo.sources.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntomologistEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    var id: Long?,
    var name: String,
    var urlPhoto: String
): BaseEntity()