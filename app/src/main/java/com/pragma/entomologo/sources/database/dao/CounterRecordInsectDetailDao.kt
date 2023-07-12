package com.pragma.entomologo.sources.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.pragma.entomologo.sources.database.views.CounterRecordInsectDetailView

@Dao
interface CounterRecordInsectDetailDao {

    @Query("SELECT * FROM CounterRecordInsectDetailView")
    suspend fun getAllCounterRecordInsectDetail() : List<CounterRecordInsectDetailView>
}