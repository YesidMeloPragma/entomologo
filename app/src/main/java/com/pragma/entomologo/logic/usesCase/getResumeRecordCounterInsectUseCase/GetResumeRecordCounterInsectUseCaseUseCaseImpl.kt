package com.pragma.entomologo.logic.usesCase.getResumeRecordCounterInsectUseCase

import com.pragma.entomologo.logic.models.ResumeRecordCounterInsectModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetResumeRecordCounterInsectUseCaseUseCaseImpl @Inject constructor(

) : GetResumeRecordCounterInsectUseCaseUseCase {

    override fun invoke(): Flow<List<ResumeRecordCounterInsectModel>> = flow {

    }
}