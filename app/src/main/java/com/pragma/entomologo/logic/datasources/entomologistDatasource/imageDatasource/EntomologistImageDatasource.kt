package com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource

import kotlinx.coroutines.flow.Flow

interface EntomologistImageDatasource {
    fun loadImageProfile(path: String) : Flow<String?>
    suspend fun saveImageProfileEntomologist(
        imageBase64: String,
        path: String,
        fileName: String
    ) : String?
}