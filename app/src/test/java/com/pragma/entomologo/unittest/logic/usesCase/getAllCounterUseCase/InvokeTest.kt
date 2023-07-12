package com.pragma.entomologo.unittest.logic.usesCase.getAllCounterUseCase

import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InvokeTest : BaseGetAllCounterTest() {

    @Test
    fun successLoadRecordsWithOneElement() = runTest {

        //Given
        val listCounterInsects = listOf(mockCounterRecordInsectModel)

        coEvery { mockCounterRecordInsectLocalDatasource.getAll() } returns listCounterInsects

        every { mockInsectModel.id } returns 1
        every { mockGeoLocationModel.id } returns 1
        every { mockCounterRecordInsectModel.insect } returns mockInsectModel
        every { mockCounterRecordInsectModel.geoLocation } returns mockGeoLocationModel


        //when
        getAllCounter
            .invoke()
            .collect{
                Assert.assertEquals(1, it.size)
            }

        //then
        coVerify(exactly = 1) { mockCounterRecordInsectLocalDatasource.getAll() }
        verify(exactly = 1) { mockInsectModel.id }
        verify(exactly = 2) { mockCounterRecordInsectModel.insect }
    }

    @Test
    fun successLoadRecordsWithZeroElement() = runTest {

        //Given
        val listCounterInsects = emptyList<CounterRecordInsectModel>()
        coEvery { mockCounterRecordInsectLocalDatasource.getAll() } returns listCounterInsects


        //when
        getAllCounter
            .invoke()
            .collect{
                Assert.assertEquals(0, it.size)
            }

        //then
        coVerify(exactly = 1) { mockCounterRecordInsectLocalDatasource.getAll() }
    }

}