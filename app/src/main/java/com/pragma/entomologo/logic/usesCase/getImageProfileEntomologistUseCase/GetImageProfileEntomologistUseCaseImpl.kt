package com.pragma.entomologo.logic.usesCase.getImageProfileEntomologistUseCase

import com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource.EntomologistImageDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource.EntomologistSPDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetImageProfileEntomologistUseCaseImpl @Inject constructor(
    private val entomologistSPDatasource: EntomologistSPDatasource,
    private val entomologistImageDatasource: EntomologistImageDatasource
) : GetImageProfileEntomologistUseCase {

    override fun invoke(): Flow<String?> = flow {
        val entomologistModel = entomologistSPDatasource.getCurrentEntomologist()
        if(entomologistModel == null) {
            emit(null)
            return@flow
        }
        val image = entomologistImageDatasource.loadImageProfile(path = entomologistModel.urlPhoto)
        emit(image)
    }.flowOn(Dispatchers.IO)
}