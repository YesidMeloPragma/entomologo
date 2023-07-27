package com.pragma.entomologo.logic.usesCase.iHaveStoragePermissionUseCase

import kotlinx.coroutines.flow.Flow

interface IHaveStoragePermissionUseCase {
    fun invoke() : Flow<Boolean>
}