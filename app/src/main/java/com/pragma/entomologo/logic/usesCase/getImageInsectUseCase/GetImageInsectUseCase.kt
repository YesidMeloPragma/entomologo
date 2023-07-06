package com.pragma.entomologo.logic.usesCase.getImageInsectUseCase

import com.pragma.entomologo.logic.models.InsectModel
import kotlinx.coroutines.flow.Flow

interface GetImageInsectUseCase {
    fun invoke(insectModel: InsectModel) : Flow<String?>
}