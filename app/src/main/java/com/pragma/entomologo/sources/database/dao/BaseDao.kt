package com.pragma.entomologo.sources.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.pragma.entomologo.sources.database.entities.BaseEntity
@Dao
interface BaseDao<T: BaseEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElement(element: T) : Long

    @Delete
    suspend fun deleteElement(vararg element: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateElement(element: T)
}