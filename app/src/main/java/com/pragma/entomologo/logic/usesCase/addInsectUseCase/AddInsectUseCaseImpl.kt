package com.pragma.entomologo.logic.usesCase.addInsectUseCase

import com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource.InsectLocalDatasource
import com.pragma.entomologo.logic.models.InsectModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddInsectUseCaseImpl @Inject constructor(
    private val insectLocalDatasource: InsectLocalDatasource
) : AddInsectUseCase {

    override fun invoke(insectModel: InsectModel): Flow<Long> = flow {
        insectLocalDatasource
            .insertInsect(insectModel = insectModel)
            .collect{ emit(it.first()) }
    }

}