package com.pragma.entomologo.sources.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.pragma.entomologo.sources.database.entities.EntomologistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntomologistDao : BaseDao<EntomologistEntity> {
    @Query("SELECT * FROM EntomologistEntity")
    fun getAll(): Flow<List<EntomologistEntity>>
}