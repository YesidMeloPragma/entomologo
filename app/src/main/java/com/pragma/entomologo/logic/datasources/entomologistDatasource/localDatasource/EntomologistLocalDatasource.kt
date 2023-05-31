package com.pragma.entomologo.logic.datasources.entomologistDatasource.localDatasource

import com.pragma.entomologo.logic.models.EntomologistModel
import kotlinx.coroutines.flow.Flow

interface EntomologistLocalDatasource {
    suspend fun delete(entomologistModel: EntomologistModel) : Boolean
    fun getAllEntomologist() : Flow<List<EntomologistModel>>
    suspend fun insert(entomologistModel: EntomologistModel) : LongArray
    suspend fun update(entomologistModel: EntomologistModel) : Boolean
}