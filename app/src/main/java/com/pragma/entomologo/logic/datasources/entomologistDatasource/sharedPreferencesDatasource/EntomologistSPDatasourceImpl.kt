package com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource

import com.pragma.entomologo.logic.models.EntomologistModel
import com.pragma.entomologo.sources.sharedPreferences.CustomSharedPreferences
import com.pragma.entomologo.sources.sharedPreferences.ElementSharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EntomologistSPDatasourceImpl @Inject constructor(
    private val customSharedPreferences: CustomSharedPreferences
) : EntomologistSPDatasource {

    private enum class Elements(private val key: String) : ElementSharedPreferences {
        CURRENT_ENTOMOLOGIST("CurrentEntomologist")
        ;
        override fun getKey() = key
    }

    override fun getCurrentEntomologist(): Flow<EntomologistModel?> = flow {
        customSharedPreferences
            .getElement(elementSharedPreferences = Elements.CURRENT_ENTOMOLOGIST, classe = EntomologistModel::class.java)
            .collect { emit(it) }
    }

    override suspend fun saveEntomologist(entomologistModel: EntomologistModel) {
        customSharedPreferences.saveElement(
            elementSharedPreferences = Elements.CURRENT_ENTOMOLOGIST,
            element = entomologistModel
        )
    }
}