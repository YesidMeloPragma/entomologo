package com.pragma.entomologo.logic.usesCase.getImageProfileEntomologistUseCase

import kotlinx.coroutines.flow.Flow

interface GetImageProfileEntomologistUseCase {
    fun invoke(): Flow<String?>
}