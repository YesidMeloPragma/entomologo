package com.pragma.entomologo.logic.usesCase.getAllCountersUseCase

import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource.CounterRecordInsectLocalDatasource
import com.pragma.entomologo.logic.datasources.insectDatasource.imageDatasource.InsectImageLocalDatasource
import com.pragma.entomologo.logic.excepciones.LogicException
import com.pragma.entomologo.logic.excepciones.TypeExceptions
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllCountersUseCaseImpl @Inject constructor(
    private val counterRecordInsectLocalDatasource: CounterRecordInsectLocalDatasource,
    private val insectImageLocalDatasource: InsectImageLocalDatasource
) : GetAllCountersUseCase {

    override fun invoke(): Flow<List<CounterRecordInsectModel>>  = flow {
        checkPermissionsStorage()
        val list = counterRecordInsectLocalDatasource.getAll()
        asignImage(list = list)
        emit(list)
    }.flowOn(Dispatchers.IO)

    private suspend fun checkPermissionsStorage() : Boolean {
        if (!insectImageLocalDatasource.iHaveStoragePermissions()) throw LogicException(typeException = TypeExceptions.WITHOUT_PERMISSIONS_STORAGE)
        return true
    }

    private suspend fun asignImage(list: List<CounterRecordInsectModel>) {
        list
            .groupBy { it.insect.id }
            .entries.forEach { entry ->
                val imageInsect = insectImageLocalDatasource.loadImageInsect(path = entry.value.first().insect.urlPhoto)
                entry.value.forEach {
                    it.imageBase64 = imageInsect
                }
            }
    }
}