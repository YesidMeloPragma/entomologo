package com.pragma.entomologo.unittest.logic.datasources.counterRecordInsectDatasource.mapper.counterRecordInsectEntityMapper

import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.mapper.convertToCounterRecordInsectModel
import com.pragma.entomologo.sources.database.entities.CounterRecordInsectEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ConvertToCounterRecordInsectModelTest : BaseCounterRecordInsectEntityMapperTest() {

    @Test
    fun mapperTest() = runTest {
        //Given
        val entity = CounterRecordInsectEntity(
            id = 1.toLong(),
            comment = "Element",
            insectId = 1,
            geoLocationId = 1,
            count = 10
        )

        //when
        val model = entity.convertToCounterRecordInsectModel()
        Assert.assertEquals(entity.id, model.id)
        Assert.assertEquals(entity.comment, model.comment)
        Assert.assertEquals(entity.count, model.count)

        //then

    }
}