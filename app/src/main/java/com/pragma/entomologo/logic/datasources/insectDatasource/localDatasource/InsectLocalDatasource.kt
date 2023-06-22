package com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource

import com.pragma.entomologo.logic.models.InsectModel
import kotlinx.coroutines.flow.Flow

interface InsectLocalDatasource {
    fun deleteInsect(insectModel: InsectModel) : Flow<Boolean>
    fun getListInsects() : Flow<List<InsectModel>>
    fun insertInsect(insectModel: InsectModel) : Flow<LongArray>
    fun updateInsect(insectModel: InsectModel) : Flow<Boolean>
}