package com.pragma.entomologo.unittest.logic.datasources.counterRecordInsectDatasource.mapper.counterRecordInsectEntityMapper

import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.mapper.convertToListCounterRecordInsectModel
import com.pragma.entomologo.sources.database.entities.CounterRecordInsectEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ListCounterRecordInsectEntityTest : BaseCounterRecordInsectEntityMapperTest() {

    @Test
    fun mapperListTest() = runTest {
        //Given
        val entity = CounterRecordInsectEntity(
            id = 1.toLong(),
            comment = "Element",
            insectId = 1,
            geoLocationId = 1,
            count = 10
        )

        val list = arrayListOf(entity)

        //when
        val listConvertered = list.convertToListCounterRecordInsectModel()
        val model = listConvertered.first()

        Assert.assertEquals(1, listConvertered.size)
        Assert.assertEquals(entity.id, model.id)
        Assert.assertEquals(entity.comment, model.comment)
        Assert.assertEquals(entity.count, model.count)

        //then
    }
}