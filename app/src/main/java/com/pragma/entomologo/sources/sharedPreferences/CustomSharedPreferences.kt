package com.pragma.entomologo.sources.sharedPreferences

interface CustomSharedPreferences {
    suspend fun <T>saveElement(elementSharedPreferences: ElementSharedPreferences, element: T) : Boolean
    suspend fun <T>getElement(
        elementSharedPreferences: ElementSharedPreferences,
        classe: Class<T>
    ) : T?
}