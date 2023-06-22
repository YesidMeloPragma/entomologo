package com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource

import com.pragma.entomologo.logic.models.EntomologistModel
import kotlinx.coroutines.flow.Flow

interface EntomologistSPDatasource {
    fun getCurrentEntomologist(): Flow<EntomologistModel?>
    suspend fun saveEntomologist(entomologistModel: EntomologistModel)
}