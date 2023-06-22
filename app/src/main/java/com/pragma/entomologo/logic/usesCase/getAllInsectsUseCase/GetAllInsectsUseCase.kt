package com.pragma.entomologo.logic.usesCase.getAllInsectsUseCase

import com.pragma.entomologo.logic.models.InsectModel
import kotlinx.coroutines.flow.Flow

interface GetAllInsectsUseCase {
    fun invoke(): Flow<List<InsectModel>>
}