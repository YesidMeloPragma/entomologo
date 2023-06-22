package com.pragma.entomologo.logic.usesCase.getAllInsectsUseCase

import com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource.InsectLocalDatasource
import com.pragma.entomologo.logic.models.InsectModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllInsectsUseCaseImpl @Inject constructor(
    private val insectLocalDatasource: InsectLocalDatasource
) : GetAllInsectsUseCase{

    override fun invoke(): Flow<List<InsectModel>> = flow {
        insectLocalDatasource
            .getListInsects()
            .collect {
                emit(it)
            }
    }
}