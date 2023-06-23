package com.pragma.entomologo.logic.usesCase.getAllCountersUseCase

import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource.CounterRecordInsectLocalDatasource
import com.pragma.entomologo.logic.datasources.geoLocationDatasource.localDatasource.GeoLocationLocalDatasource
import com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource.InsectLocalDatasource
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import com.pragma.entomologo.logic.models.GeoLocationModel
import com.pragma.entomologo.logic.models.InsectModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllCountersUseCaseImpl @Inject constructor(
    private val counterRecordInsectLocalDatasource: CounterRecordInsectLocalDatasource,
    private val insectLocalDatasource: InsectLocalDatasource,
    private val geoLocationLocalDatasource: GeoLocationLocalDatasource,
) : GetAllCountersUseCase {

    override fun invoke(): Flow<List<CounterRecordInsectModel>>  = flow {
        counterRecordInsectLocalDatasource.getAll().collect{
            listCounters ->
            insectLocalDatasource.getListInsects().collect{
                listInsects ->

                geoLocationLocalDatasource.getAllGeoLocations().collect{
                    listGeoLocations ->

                    for (item in listCounters) {
                        item.insect = listInsects.find { it.id == item.insect.id } ?: InsectModel(specieName = "", urlPhoto = "", moreInformation = "")
                        item.geoLocation = listGeoLocations.find { it.id == item.geoLocation.id }?: GeoLocationModel(lat = 0.0, lng = 0.0)
                    }
                    emit(listCounters)
                }
            }
        }
    }.flowOn(Dispatchers.IO)
}