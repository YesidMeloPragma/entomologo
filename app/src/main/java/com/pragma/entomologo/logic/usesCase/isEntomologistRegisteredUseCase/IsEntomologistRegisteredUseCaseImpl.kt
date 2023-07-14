package com.pragma.entomologo.logic.usesCase.isEntomologistRegisteredUseCase

import com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource.EntomologistSPDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsEntomologistRegisteredUseCaseImpl @Inject constructor(
    private val entomologistSPDatasource: EntomologistSPDatasource
) : IsEntomologistRegisteredUseCase {

    override fun invoke(): Flow<Boolean> = flow {
        emit(entomologistSPDatasource.getCurrentEntomologist() != null)
    }
}