package com.pragma.entomologo.logic.datasources.insectDatasource.imageDatasource

import kotlinx.coroutines.flow.Flow

interface InsectImageLocalDatasource {
    suspend fun existsImage(path: String): Boolean
    fun loadImageInsect(path: String) : Flow<String?>
    suspend fun saveImageInsect(
        imageBase64: String,
        path: String,
        fileName: String
    ) : String?
}