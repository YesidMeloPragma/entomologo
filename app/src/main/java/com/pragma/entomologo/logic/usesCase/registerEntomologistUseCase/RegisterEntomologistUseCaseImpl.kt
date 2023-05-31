package com.pragma.entomologo.logic.usesCase.registerEntomologistUseCase

import com.pragma.entomologo.logic.constants.DirNamesEnum
import com.pragma.entomologo.logic.constants.FileNamesEnum
import com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource.EntomologistImageDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.localDatasource.EntomologistLocalDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource.EntomologistSPDatasource
import com.pragma.entomologo.logic.models.EntomologistModel
import javax.inject.Inject

class RegisterEntomologistUseCaseImpl @Inject constructor(
    private val entomologistImageDatasource: EntomologistImageDatasource,
    private val entomologistLocalDatasource: EntomologistLocalDatasource,
    private val entomologistSPDatasource: EntomologistSPDatasource
): RegisterEntomologistUseCase {

    override suspend fun invoke(bitmapBase64: String, entomologistModel: EntomologistModel): Boolean {

        //region save image in device

        val pathImageSaved = entomologistImageDatasource.saveImageProfileEntomologist(
            imageBase64 = bitmapBase64,
            path = DirNamesEnum.IMAGES_APP.getDirName(),
            fileName = FileNamesEnum.PROFILE_IMAGE.getFileName()
        ) ?: return false

        entomologistModel.apply { urlPhoto = pathImageSaved }
        //endregion

        //region save in db the entomologist
        val registerCompleted =  entomologistLocalDatasource.insert(entomologistModel = entomologistModel)
        entomologistModel.apply { id = registerCompleted.first() }
        //endregion

        //region save in sharedpreference the profile
        entomologistSPDatasource.saveEntomologist(entomologistModel = entomologistModel)
        //endregion

        return true
    }
}