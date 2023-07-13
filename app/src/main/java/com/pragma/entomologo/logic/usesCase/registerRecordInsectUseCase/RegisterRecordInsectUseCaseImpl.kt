package com.pragma.entomologo.logic.usesCase.registerRecordInsectUseCase

import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource.CounterRecordInsectLocalDatasource
import com.pragma.entomologo.logic.datasources.geoLocationDatasource.localDatasource.GeoLocationLocalDatasource
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterRecordInsectUseCaseImpl @Inject constructor(
    private val counterRecordInsectLocalDatasource: CounterRecordInsectLocalDatasource,
    private val geoLocationLocalDatasource: GeoLocationLocalDatasource
) : RegisterRecordInsectUseCase {

    override fun invoke(counterRecordInsectModel: CounterRecordInsectModel): Flow<Boolean>  = flow {
        val geoLocationsId = geoLocationLocalDatasource.insertGeoLocation(geoLocationModel = counterRecordInsectModel.geoLocation)
        counterRecordInsectModel.geoLocation.apply { id = geoLocationsId.last() }
        counterRecordInsectLocalDatasource.insert(counterRecordInsectModel = counterRecordInsectModel)
        emit(true)
    }
}