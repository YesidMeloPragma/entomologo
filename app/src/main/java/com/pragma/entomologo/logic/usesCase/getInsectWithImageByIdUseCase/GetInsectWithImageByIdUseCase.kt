package com.pragma.entomologo.logic.usesCase.getInsectWithImageByIdUseCase

import com.pragma.entomologo.logic.models.InsectModel
import kotlinx.coroutines.flow.Flow

interface GetInsectWithImageByIdUseCase {
    fun invoke(insectId : Long) : Flow<Pair<InsectModel, String?>>
}