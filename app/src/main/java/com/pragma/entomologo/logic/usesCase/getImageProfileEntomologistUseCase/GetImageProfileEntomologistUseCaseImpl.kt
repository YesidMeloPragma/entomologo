@file:Suppress("LABEL_NAME_CLASH")

package com.pragma.entomologo.logic.usesCase.getImageProfileEntomologistUseCase

import com.pragma.entomologo.logic.constants.DirNamesEnum
import com.pragma.entomologo.logic.constants.FileNamesEnum
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
        entomologistSPDatasource
            .getCurrentEntomologist()
            .collect{ entomologistModel ->
                if(entomologistModel == null) {
                    emit(null)
                    return@collect
                }
                entomologistImageDatasource
                    .loadImageProfile(path = "${DirNamesEnum.IMAGES_APP.getDirName()}/${FileNamesEnum.PROFILE_IMAGE.getFileName()}")
                    .collect { imageBase64 ->
                        if (imageBase64 == null) {
                            emit(null)
                            return@collect
                        }
                        emit(imageBase64)
                    }
            }
    }.flowOn(Dispatchers.IO)
}