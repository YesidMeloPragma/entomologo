package com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource

import com.pragma.entomologo.sources.appImageGallery.ImageAppGallery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EntomologistImageDatasourceImpl @Inject constructor(
    private val imageAppGallery: ImageAppGallery
) : EntomologistImageDatasource {

    override fun loadImageProfile(path: String): Flow<String?> = flow{
        imageAppGallery
            .getImageStringBase64(path = path)
            .collect { emit(it) }
    }

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