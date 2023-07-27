package com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource

interface EntomologistImageDatasource {
    suspend fun iHaveStoragePermissions(): Boolean
    suspend fun loadImageProfile(path: String) : String?
    suspend fun saveImageProfileEntomologist(
        imageBase64: String,
        path: String,
        fileName: String
    ) : String?
}