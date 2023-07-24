package com.pragma.entomologo.logic.usesCase.iHaveGPSPermissionUseCase

import com.pragma.entomologo.logic.datasources.geoLocationDatasource.gpsDatasource.GPSDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IHaveGPSPermissionUseCaseImpl @Inject constructor(
    private val gpsDatasource: GPSDatasource
) : IHaveGPSPermissionUseCase {

    override fun invoke(): Flow<Boolean> = flow {
        val iHavePermission = gpsDatasource.iHaveGPSPermissions()
        emit(iHavePermission)
    }
}