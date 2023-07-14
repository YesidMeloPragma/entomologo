package com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource

import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.mapper.convertToCounterRecordInsectEntity
import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.mapper.toListCounterRecordInsectModel
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import com.pragma.entomologo.sources.database.dao.CounterRecordInsectDao
import com.pragma.entomologo.sources.database.dao.CounterRecordInsectDetailDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CounterRecordInsectLocalDatasourceImpl @Inject constructor(
    private val counterRecordInsectDao: CounterRecordInsectDao,
    private val counterRecordInsectDetailDao: CounterRecordInsectDetailDao
) : CounterRecordInsectLocalDatasource {

    override fun delete(counterRecordInsectModel: CounterRecordInsectModel): Flow<Boolean> = flow {
        counterRecordInsectDao.deleteElement(counterRecordInsectModel.convertToCounterRecordInsectEntity())
        emit(true)
    }.flowOn(Dispatchers.IO)

    override suspend fun getAll(): List<CounterRecordInsectModel> {
        return counterRecordInsectDetailDao.getAllCounterRecordInsectDetail().toListCounterRecordInsectModel()
    }

    override suspend fun insert(counterRecordInsectModel: CounterRecordInsectModel): LongArray
            = counterRecordInsectDao.insertElement(counterRecordInsectModel.convertToCounterRecordInsectEntity())

    override fun update(counterRecordInsectModel: CounterRecordInsectModel): Flow<Boolean> = flow {
        counterRecordInsectDao.updateElement(counterRecordInsectModel.convertToCounterRecordInsectEntity())
        emit(true)
    }.flowOn(Dispatchers.IO)
}