package com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource

import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import kotlinx.coroutines.flow.Flow

interface CounterRecordInsectLocalDatasource {
    fun delete(counterRecordInsectModel: CounterRecordInsectModel): Flow<Boolean>
    fun getAll(): Flow<List<CounterRecordInsectModel>>
    fun insert(counterRecordInsectModel: CounterRecordInsectModel): Flow<LongArray>
    fun update(counterRecordInsectModel: CounterRecordInsectModel): Flow<Boolean>
}