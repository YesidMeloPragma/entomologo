package com.pragma.entomologo.sources.sharedPreferences

import kotlinx.coroutines.flow.Flow

interface CustomSharedPreferences {
    suspend fun <T>saveElement(elementSharedPreferences: ElementSharedPreferences, element: T) : Boolean
    fun <T>getElement(
        elementSharedPreferences: ElementSharedPreferences,
        classe: Class<T>
    ) : Flow<T?>
}