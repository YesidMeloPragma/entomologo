package com.pragma.entomologo.logic.usesCase.addInsectUseCase

import com.pragma.entomologo.logic.constants.DirNamesEnum
import com.pragma.entomologo.logic.datasources.insectDatasource.imageDatasource.InsectImageLocalDatasource
import com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource.InsectLocalDatasource
import com.pragma.entomologo.logic.excepciones.LogicException
import com.pragma.entomologo.logic.excepciones.TypeExceptions
import com.pragma.entomologo.logic.models.InsectModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddInsectUseCaseImpl @Inject constructor(
    private val insectLocalDatasource: InsectLocalDatasource,
    private val insectImageLocalDatasource: InsectImageLocalDatasource
) : AddInsectUseCase {

    override fun invoke(imageBase64: String, insectModel: InsectModel): Flow<Long> = flow {
        validInformation(imageBase64 = imageBase64, insectModel = insectModel)
        val pathImage = "${DirNamesEnum.INSECT_RECORDS.getDirName()}/${insectModel.specieName.lowercase()}.jpg"
        saveImage(imageBase64 = imageBase64, insectModel = insectModel, path = pathImage)
        saveInsectInDB(insectModel = insectModel, path = pathImage)
            .collect{ emit(it) }
    }

    private fun validInformation(imageBase64: String, insectModel: InsectModel) {
        if(insectModel.specieName.isEmpty())  throw LogicException(typeException = TypeExceptions.WITHOUT_NAMESPECIE)
        if (imageBase64.isEmpty() && insectModel.urlPhoto.isEmpty()) throw LogicException(typeException = TypeExceptions.WITHOUT_IMAGE_SPECIE)
        if(insectModel.moreInformation.isEmpty()) throw LogicException(typeException = TypeExceptions.WITHOUT_URL_DESCRIPTION)
    }

    private suspend fun saveImage(imageBase64: String, insectModel: InsectModel, path: String) {
        val existsImage = insectImageLocalDatasource.existsImage(path = path)
        if(!existsImage) {
            insectImageLocalDatasource
                .saveImageInsect(
                    imageBase64 = imageBase64,
                    path = DirNamesEnum.INSECT_RECORDS.getDirName(),
                    fileName = "${insectModel.specieName.lowercase()}.jpg"
                )
        }
    }

    private fun saveInsectInDB(insectModel: InsectModel, path: String) : Flow<Long> = flow {
        val existsInsect = insectLocalDatasource.existsInsect(nameSpecie = insectModel.specieName.lowercase())
        if (existsInsect) {
            emit(1)
            return@flow
        }
        insectLocalDatasource
            .insertInsect(
                insectModel = InsectModel(
                    id = insectModel.id,
                    specieName = insectModel.specieName.lowercase(),
                    moreInformation = insectModel.moreInformation.lowercase(),
                    urlPhoto = insectModel.urlPhoto.lowercase().ifEmpty { path }
                )
            )
            .collect{ emit(it.first()) }
    }
}