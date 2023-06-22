package com.pragma.entomologo.sources.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.pragma.entomologo.sources.database.entities.GeoLocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GeoLocationDao : BaseDao<GeoLocationEntity> {

    @Query("SELECT * FROM GeoLocationEntity")
    fun getAllGeoLocations(): Flow<List<GeoLocationEntity>>
}