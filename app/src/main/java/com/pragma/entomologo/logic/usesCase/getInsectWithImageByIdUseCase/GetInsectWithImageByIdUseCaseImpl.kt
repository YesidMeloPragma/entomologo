package com.pragma.entomologo.logic.usesCase.getInsectWithImageByIdUseCase

import com.pragma.entomologo.logic.datasources.insectDatasource.imageDatasource.InsectImageLocalDatasource
import com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource.InsectLocalDatasource
import com.pragma.entomologo.logic.models.InsectModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetInsectWithImageByIdUseCaseImpl @Inject constructor(
    private val insectImageLocalDatasource: InsectImageLocalDatasource,
    private val insectLocalDatasource: InsectLocalDatasource,
) : GetInsectWithImageByIdUseCase {

    override fun invoke(insectId: Long): Flow<Pair<InsectModel, String?>> = flow {
        val model = insectLocalDatasource.getInsectModelById(insectId = insectId)
        val imageBase64 = insectImageLocalDatasource.loadImageInsect(path = model.urlPhoto)
        emit(Pair(first = model, second = imageBase64))
    }
}