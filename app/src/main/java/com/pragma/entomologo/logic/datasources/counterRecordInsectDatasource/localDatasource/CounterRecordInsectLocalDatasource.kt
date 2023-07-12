package com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource

import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import kotlinx.coroutines.flow.Flow

interface CounterRecordInsectLocalDatasource {
    suspend fun delete(counterRecordInsectModel: CounterRecordInsectModel): Boolean
    suspend fun getAll(): List<CounterRecordInsectModel>
    suspend fun insert(counterRecordInsectModel: CounterRecordInsectModel): LongArray
    fun update(counterRecordInsectModel: CounterRecordInsectModel): Flow<Boolean>
}