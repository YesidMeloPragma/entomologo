package com.pragma.entomologo.sources.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.pragma.entomologo.sources.database.entities.CounterRecordInsectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CounterRecordInsectDao : BaseDao<CounterRecordInsectEntity> {

    @Query("SELECT * FROM CounterRecordInsectEntity")
    fun getAll(): Flow<List<CounterRecordInsectEntity>>
}