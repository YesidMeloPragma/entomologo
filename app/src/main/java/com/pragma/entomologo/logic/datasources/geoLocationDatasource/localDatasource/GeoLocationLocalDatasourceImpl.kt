package com.pragma.entomologo.logic.datasources.geoLocationDatasource.localDatasource

import com.pragma.entomologo.logic.datasources.geoLocationDatasource.mappers.convertToListGeoLocationModel
import com.pragma.entomologo.logic.models.GeoLocationModel
import com.pragma.entomologo.sources.database.dao.GeoLocationDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GeoLocationLocalDatasourceImpl @Inject constructor(
    private val geoLocationDao: GeoLocationDao
) : GeoLocationLocalDatasource {
    override fun deleteGeoLocation(geoLocationModel: GeoLocationModel): Flow<Boolean?> = flow {
        geoLocationDao.deleteElement( geoLocationModel.convertToListGeoLocationModel() )
        emit(true)
    }.flowOn(Dispatchers.IO)

    override fun getAllGeoLocations(): Flow<List<GeoLocationModel>> = flow {
        geoLocationDao
            .getAllGeoLocations()
            .collect {
            emit(it.convertToListGeoLocationModel())
        }
    }.flowOn(Dispatchers.IO)

    override fun insertGeoLocation(geoLocationModel: GeoLocationModel): Flow<LongArray> = flow{
        val ids = geoLocationDao.insertElement(geoLocationModel.convertToListGeoLocationModel())
        emit(ids)
    }.flowOn(Dispatchers.IO)

    override fun updateGeoLocation(geoLocationModel: GeoLocationModel): Flow<Boolean> = flow {
        geoLocationDao.updateElement(geoLocationModel.convertToListGeoLocationModel())
        emit(true)
    }.flowOn(Dispatchers.IO)
}