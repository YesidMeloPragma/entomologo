package com.pragma.entomologo.logic.datasources.insectDatasource.imageDatasource

import com.pragma.entomologo.sources.appImageGallery.ImageAppGallery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsectImageLocalDatasourceImpl @Inject constructor(
    private val imageAppGallery: ImageAppGallery
) : InsectImageLocalDatasource {

    override suspend fun existsImage(path: String): Boolean = imageAppGallery.existsImage(path = path)

    override fun loadImageInsect(path: String): Flow<String?> = flow {
        imageAppGallery
            .getImageStringBase64(path = path)
            .collect { emit(it) }
    }

    override suspend fun saveImageInsect(
        imageBase64: String,
        path: String,
        fileName: String
    ) = imageAppGallery.saveImage(
        stringBase64 = imageBase64,
        path = path,
        fileName = fileName
    )
}