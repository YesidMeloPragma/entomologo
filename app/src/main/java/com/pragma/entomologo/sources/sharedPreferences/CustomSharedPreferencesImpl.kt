package com.pragma.entomologo.sources.sharedPreferences

import android.content.Context
import com.google.gson.Gson

class CustomSharedPreferencesImpl(context: Context) : CustomSharedPreferences {
    private val nameSP = "SharedPreferences"
    private val preferences = context.getSharedPreferences(nameSP, Context.MODE_PRIVATE)

    override suspend fun <T> saveElement(
        elementSharedPreferences: ElementSharedPreferences,
        element: T
    ): Boolean{
        val gson = Gson()
        val objToSave = gson.toJson(element)
        with(preferences.edit()) {
            putString(elementSharedPreferences.getKey(), objToSave)
            apply()
        }
        return true
    }

    override suspend fun <T> getElement(
        elementSharedPreferences: ElementSharedPreferences,
        classe: Class<T>
    ): T? {
        val gson = Gson()
        val objJson = preferences.getString(elementSharedPreferences.getKey(), null) ?: return null
        return gson.fromJson(objJson, classe)
    }


    companion object {
        private var instance : CustomSharedPreferencesImpl? = null

        fun getInstance() = instance!!

        fun initInstance(context: Context) {
            if(instance != null) return
            instance = CustomSharedPreferencesImpl(context = context)
        }
    }

}