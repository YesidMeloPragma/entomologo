package com.pragma.entomologo.logic.usesCase.registerRecordInsectUseCase

import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource.CounterRecordInsectLocalDatasource
import com.pragma.entomologo.logic.datasources.geoLocationDatasource.gpsDatasource.GPSDatasource
import com.pragma.entomologo.logic.datasources.geoLocationDatasource.localDatasource.GeoLocationLocalDatasource
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import com.pragma.entomologo.logic.models.GeoLocationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterRecordInsectUseCaseImpl @Inject constructor(
    private val counterRecordInsectLocalDatasource: CounterRecordInsectLocalDatasource,
    private val geoLocationLocalDatasource: GeoLocationLocalDatasource,
    private val gpsDatasource: GPSDatasource
) : RegisterRecordInsectUseCase {

    override fun invoke(counterRecordInsectModel: CounterRecordInsectModel): Flow<Boolean>  = flow {
        val currentLocation = gpsDatasource.getLocation()
        val geoLocationsId = geoLocationLocalDatasource.insertGeoLocation(geoLocationModel = GeoLocationModel(
            lat = currentLocation.first.first,
            lng = currentLocation.first.second,
            city = currentLocation.second
        ))
        counterRecordInsectModel.geoLocation.apply { id = geoLocationsId.last() }
        counterRecordInsectLocalDatasource.insert(counterRecordInsectModel = counterRecordInsectModel)
        emit(true)
    }
}