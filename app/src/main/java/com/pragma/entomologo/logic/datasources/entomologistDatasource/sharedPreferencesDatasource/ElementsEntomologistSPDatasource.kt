package com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource

import com.pragma.entomologo.sources.sharedPreferences.ElementSharedPreferences

enum class ElementsEntomologistSPDatasource constructor(
    private val key: String
): ElementSharedPreferences{
        CURRENT_ENTOMOLOGIST("CurrentEntomologist")
        ;
        override fun getKey() = key
}