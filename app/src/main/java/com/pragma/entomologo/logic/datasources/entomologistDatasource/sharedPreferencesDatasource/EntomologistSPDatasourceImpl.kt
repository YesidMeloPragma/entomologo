package com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource

import com.pragma.entomologo.logic.models.EntomologistModel
import com.pragma.entomologo.sources.sharedPreferences.CustomSharedPreferences
import javax.inject.Inject

class EntomologistSPDatasourceImpl @Inject constructor(
    private val customSharedPreferences: CustomSharedPreferences
) : EntomologistSPDatasource {

    override suspend fun getCurrentEntomologist(): EntomologistModel?
        = customSharedPreferences
        .getElement(
            elementSharedPreferences = ElementsEntomologistSPDatasource.CURRENT_ENTOMOLOGIST,
            classe = EntomologistModel::class.java
        )

    override suspend fun saveEntomologist(entomologistModel: EntomologistModel) {
        customSharedPreferences.saveElement(
            elementSharedPreferences = ElementsEntomologistSPDatasource.CURRENT_ENTOMOLOGIST,
            element = entomologistModel
        )
    }
}