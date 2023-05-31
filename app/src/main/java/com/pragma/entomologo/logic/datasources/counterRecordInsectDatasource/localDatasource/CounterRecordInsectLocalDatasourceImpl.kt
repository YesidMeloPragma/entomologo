package com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource

import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.mapper.convertToCounterRecordInsectEntity
import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.mapper.convertToListCounterRecordInsectModel
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import com.pragma.entomologo.sources.database.dao.CounterRecordInsectDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CounterRecordInsectLocalDatasourceImpl @Inject constructor(
    private val counterRecordInsectDao: CounterRecordInsectDao
) : CounterRecordInsectLocalDatasource {

    override fun delete(counterRecordInsectModel: CounterRecordInsectModel): Flow<Boolean> = flow {
        counterRecordInsectDao.deleteElement(counterRecordInsectModel.convertToCounterRecordInsectEntity())
        emit(true)
    }.flowOn(Dispatchers.IO)

    override fun getAll(): Flow<List<CounterRecordInsectModel>> = flow {
        counterRecordInsectDao
            .getAll()
            .collect{
                emit(it.convertToListCounterRecordInsectModel())
            }
    }.flowOn(Dispatchers.IO)

    override fun insert(counterRecordInsectModel: CounterRecordInsectModel): Flow<LongArray> = flow {
        val ids = counterRecordInsectDao.insertElement(counterRecordInsectModel.convertToCounterRecordInsectEntity())
        emit(ids)
    }.flowOn(Dispatchers.IO)

    override fun update(counterRecordInsectModel: CounterRecordInsectModel): Flow<Boolean> = flow {
        counterRecordInsectDao.updateElement(counterRecordInsectModel.convertToCounterRecordInsectEntity())
        emit(true)
    }.flowOn(Dispatchers.IO)
}