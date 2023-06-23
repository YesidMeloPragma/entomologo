package com.pragma.entomologo.logic.usesCase.addInsectUseCase

import com.pragma.entomologo.logic.models.InsectModel
import kotlinx.coroutines.flow.Flow

interface AddInsectUseCase {
    fun invoke(
        imageBase64: String,
        insectModel: InsectModel,
    ): Flow<Long>
}