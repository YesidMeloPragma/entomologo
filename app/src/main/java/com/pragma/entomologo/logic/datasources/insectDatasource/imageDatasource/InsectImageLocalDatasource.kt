package com.pragma.entomologo.logic.datasources.insectDatasource.imageDatasource

interface InsectImageLocalDatasource {
    suspend fun existsImage(path: String): Boolean
    suspend fun loadImageInsect(path: String) : String?
    suspend fun saveImageInsect(
        imageBase64: String,
        path: String,
        fileName: String
    ) : String?
}