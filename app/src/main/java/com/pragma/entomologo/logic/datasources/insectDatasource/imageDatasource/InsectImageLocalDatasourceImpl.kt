package com.pragma.entomologo.logic.datasources.insectDatasource.imageDatasource

import com.pragma.entomologo.sources.appImageGallery.ImageAppGallery
import javax.inject.Inject

class InsectImageLocalDatasourceImpl @Inject constructor(
    private val imageAppGallery: ImageAppGallery
) : InsectImageLocalDatasource {

    override suspend fun existsImage(path: String): Boolean = imageAppGallery.existsImage(path = path)
    override suspend fun iHaveStoragePermissions(): Boolean
        = imageAppGallery.iHaveStoragePermissions()

    override suspend fun loadImageInsect(path: String): String? = imageAppGallery.getImageStringBase64(path = path)

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