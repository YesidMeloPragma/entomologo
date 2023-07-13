package com.pragma.entomologo.sources.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.pragma.entomologo.sources.database.entities.InsectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InsectDao : BaseDao<InsectEntity> {

    @Query("SELECT * FROM InsectEntity")
    fun getAllInsects(): Flow<List<InsectEntity>>

    @Query("SELECT EXISTS(SELECT * FROM InsectEntity WHERE specieName = :nameSpecie)")
    suspend fun existsInsect(nameSpecie : String) : Boolean

    @Query("SELECT * FROM InsectEntity WHERE id = :insectId")
    suspend fun getInsectEntityById(insectId: Long): InsectEntity

    @Query("SELECT id FROM InsectEntity WHERE specieName = :nameSpecie")
    suspend fun getInsectIdByName(nameSpecie: String): Long
}