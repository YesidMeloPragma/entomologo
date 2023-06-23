package com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource

import com.pragma.entomologo.logic.datasources.insectDatasource.mappers.convertToInsectEntity
import com.pragma.entomologo.logic.datasources.insectDatasource.mappers.convertToListInsectModel
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.sources.database.dao.InsectDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InsectLocalDatasourceImpl @Inject constructor(
    private val insectDao: InsectDao
) : InsectLocalDatasource {

    override fun deleteInsect(insectModel: InsectModel): Flow<Boolean> = flow {
        insectDao.deleteElement(insectModel.convertToInsectEntity())
        emit(true)
    }.flowOn(Dispatchers.IO)

    override suspend fun existsInsect(
        nameSpecie: String
    ): Boolean = insectDao.existsInsect(
        nameSpecie = nameSpecie
    )

    override fun getListInsects(): Flow<List<InsectModel>> = flow {
        insectDao.getAllInsects().collect{
            emit(it.convertToListInsectModel())
        }
    }.flowOn(context = Dispatchers.IO)

    override fun insertInsect(insectModel: InsectModel): Flow<LongArray> = flow{
        val ids = insectDao.insertElement(insectModel.convertToInsectEntity())
        emit(ids)
    }.flowOn(Dispatchers.IO)

    override fun updateInsect(insectModel: InsectModel): Flow<Boolean> =  flow {
        insectDao.updateElement(insectModel.convertToInsectEntity())
        emit(true)
    }

}