package com.pragma.entomologo.unittest.logic.datasources.counterRecordInsectDatasource.mapper.counterRecordInsectModelMapper

import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.mapper.convertToCounterRecordInsectEntity
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ConvertToCounterRecordInsectTest : BaseBaseCounterRecordInsectModelTestTest() {

    @Test
    fun exampleFunctionTest() = runTest {

        //Given
        val model = CounterRecordInsectModel(
            id = 1,
            comment = "comment",
            insect = mockInsectModel,
            geoLocation = mockGeoLocationModel,
            count = 3
        )
        every { mockInsectModel.id } returns 1
        every { mockGeoLocationModel.id } returns 1

        //when
        val entity = model.convertToCounterRecordInsectEntity()
        Assert.assertEquals(model.id, entity.id)
        Assert.assertEquals(model.comment, entity.comment)
        Assert.assertEquals(model.insect, mockInsectModel)
        Assert.assertEquals(model.geoLocation, mockGeoLocationModel)
        Assert.assertEquals(model.count, entity.count)

        //then
        verify(exactly = 1) { mockInsectModel.id }
        verify(exactly = 1) { mockGeoLocationModel.id }
    }
}