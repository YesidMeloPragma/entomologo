package com.pragma.entomologo.logic.usesCase.isEntomologistRegisteredUseCase

import kotlinx.coroutines.flow.Flow

interface IsEntomologistRegisteredUseCase {
    fun invoke() : Flow<Boolean>
}