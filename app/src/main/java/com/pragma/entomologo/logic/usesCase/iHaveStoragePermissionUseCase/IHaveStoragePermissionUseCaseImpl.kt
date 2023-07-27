package com.pragma.entomologo.logic.usesCase.iHaveStoragePermissionUseCase

import com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource.EntomologistImageDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IHaveStoragePermissionUseCaseImpl @Inject constructor(
    private val entomologistImageDatasource: EntomologistImageDatasource
) : IHaveStoragePermissionUseCase {

    override fun invoke(): Flow<Boolean> = flow {
        emit(entomologistImageDatasource.iHaveStoragePermissions())
    }
}