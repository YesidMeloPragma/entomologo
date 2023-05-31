package com.pragma.entomologo.sources.sharedPreferences

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

    override fun <T> getElement(elementSharedPreferences: ElementSharedPreferences, classe: Class<T>): Flow<T?> = flow {
        val gson = Gson()
        val objJson = preferences.getString(elementSharedPreferences.getKey(), null)
        if (objJson == null) {
            emit(null)
            return@flow
        }
        val obj = gson.fromJson(objJson, classe)
        emit(obj)
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