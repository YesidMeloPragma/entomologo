package com.pragma.entomologo.logic.usesCase.registerRecordInsectUseCase

import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import kotlinx.coroutines.flow.Flow

interface RegisterRecordInsectUseCase {
    fun invoke(counterRecordInsectModel: CounterRecordInsectModel): Flow<Boolean>
}