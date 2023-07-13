package com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource

interface EntomologistImageDatasource {
    suspend fun loadImageProfile(path: String) : String?
    suspend fun saveImageProfileEntomologist(
        imageBase64: String,
        path: String,
        fileName: String
    ) : String?
}