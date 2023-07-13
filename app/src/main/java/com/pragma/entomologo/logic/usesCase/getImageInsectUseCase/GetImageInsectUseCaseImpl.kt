package com.pragma.entomologo.logic.usesCase.getImageInsectUseCase

import com.pragma.entomologo.logic.datasources.insectDatasource.imageDatasource.InsectImageLocalDatasource
import com.pragma.entomologo.logic.models.InsectModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetImageInsectUseCaseImpl @Inject constructor(
    private val insectImageLocalDatasource: InsectImageLocalDatasource
) : GetImageInsectUseCase {

    override fun invoke(insectModel: InsectModel): Flow<String?> = flow {
        if (insectModel.urlPhoto.isEmpty()) {
            emit(null)
            return@flow
        }
        val image = insectImageLocalDatasource.loadImageInsect(path = insectModel.urlPhoto)
        emit(image)
    }
}