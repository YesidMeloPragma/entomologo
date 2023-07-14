package com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource

import com.pragma.entomologo.logic.models.EntomologistModel

interface EntomologistSPDatasource {
    suspend fun getCurrentEntomologist(): EntomologistModel?
    suspend fun saveEntomologist(entomologistModel: EntomologistModel)
}