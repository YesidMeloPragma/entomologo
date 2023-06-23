package com.pragma.entomologo.unittest.logic.datasources.counterRecordInsectDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DeleteTest : BaseCounterRecordInsectLocalDatasourceTest() {

    @Test
    fun successDelete() = runTest {
        //Given
        val comment = "comment"
        val count = 1
        coEvery { mockCounterRecordInsectModel.id } returns 1
        coEvery { mockCounterRecordInsectModel.insect } returns mockInsectModel
        coEvery { mockCounterRecordInsectModel.geoLocation } returns mockGeoLocationModel
        coEvery { mockCounterRecordInsectModel.comment } returns comment
        coEvery { mockCounterRecordInsectModel.count } returns count
        coEvery { mockCounterRecordDao.deleteElement(element = anyVararg() )} answers {}

        //when
        counterRecordInsectLocalDatasource
            .delete(counterRecordInsectModel = mockCounterRecordInsectModel)
            .collect{
                Assert.assertEquals(true, it)
            }

        //then
        coVerify(exactly = 1) { mockCounterRecordInsectModel.id }
        coVerify(exactly = 1) { mockCounterRecordInsectModel.insect }
        coVerify(exactly = 1) { mockCounterRecordInsectModel.geoLocation }
        coVerify(exactly = 1) { mockCounterRecordInsectModel.comment }
        coVerify(exactly = 1) { mockCounterRecordInsectModel.count }
        coVerify(exactly = 1) { mockCounterRecordDao.deleteElement(element = anyVararg())}
    }
}