package com.pragma.entomologo.logic.usesCase.getAllCountersUseCase

import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import kotlinx.coroutines.flow.Flow

interface GetAllCountersUseCase {
    fun invoke() : Flow<List<CounterRecordInsectModel>>
}