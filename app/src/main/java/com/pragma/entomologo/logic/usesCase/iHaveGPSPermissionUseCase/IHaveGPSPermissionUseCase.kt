package com.pragma.entomologo.logic.usesCase.iHaveGPSPermissionUseCase

import kotlinx.coroutines.flow.Flow

interface IHaveGPSPermissionUseCase {
    fun invoke(): Flow<Boolean>
}