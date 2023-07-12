package com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource

import com.pragma.entomologo.sources.appImageGallery.ImageAppGallery
import javax.inject.Inject

class EntomologistImageDatasourceImpl @Inject constructor(
    private val imageAppGallery: ImageAppGallery
) : EntomologistImageDatasource {

    override suspend fun loadImageProfile(path: String): String? = imageAppGallery.getImageStringBase64(path = path)

    override suspend fun saveImageProfileEntomologist(
        imageBase64: String,
        path: String,
        fileName: String
    ) = imageAppGallery.saveImage(
        stringBase64 = imageBase64,
        path = path,
        fileName = fileName
    )

}