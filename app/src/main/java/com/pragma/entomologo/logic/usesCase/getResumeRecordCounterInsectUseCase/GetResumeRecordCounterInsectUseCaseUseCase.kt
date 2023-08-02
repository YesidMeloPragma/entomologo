package com.pragma.entomologo.logic.usesCase.getResumeRecordCounterInsectUseCase

import com.pragma.entomologo.logic.models.ResumeRecordCounterInsectModel
import kotlinx.coroutines.flow.Flow

interface GetResumeRecordCounterInsectUseCaseUseCase {
    fun invoke(): Flow<List<ResumeRecordCounterInsectModel>>
}