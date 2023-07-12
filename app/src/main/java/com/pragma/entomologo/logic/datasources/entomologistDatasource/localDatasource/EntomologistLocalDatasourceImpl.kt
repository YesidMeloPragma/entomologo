package com.pragma.entomologo.logic.datasources.entomologistDatasource.localDatasource

import com.pragma.entomologo.logic.datasources.entomologistDatasource.mapper.convertToEntomologistEntity
import com.pragma.entomologo.logic.datasources.entomologistDatasource.mapper.convertToListEntomologistModel
import com.pragma.entomologo.logic.models.EntomologistModel
import com.pragma.entomologo.sources.database.dao.EntomologistDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EntomologistLocalDatasourceImpl @Inject constructor(
    private val entomologistDao: EntomologistDao
) : EntomologistLocalDatasource {

    override suspend fun delete(entomologistModel: EntomologistModel): Boolean {
        entomologistDao.deleteElement(entomologistModel.convertToEntomologistEntity())
        return true
    }

    override fun getAllEntomologist(): Flow<List<EntomologistModel>> = flow{
        entomologistDao.getAll().collect{
            emit(it.convertToListEntomologistModel())
        }
    }

    override suspend fun insert(entomologistModel: EntomologistModel): LongArray {
        return arrayOf(entomologistDao.insertElement(entomologistModel.convertToEntomologistEntity())).toLongArray()
    }

    override suspend fun update(entomologistModel: EntomologistModel): Boolean {
        entomologistDao.updateElement(entomologistModel.convertToEntomologistEntity())
        return true
    }

}