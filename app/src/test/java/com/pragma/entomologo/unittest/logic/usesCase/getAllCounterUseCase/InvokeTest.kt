package com.pragma.entomologo.unittest.logic.usesCase.getAllCounterUseCase

import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import com.pragma.entomologo.logic.models.GeoLocationModel
import com.pragma.entomologo.logic.models.InsectModel
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InvokeTest : BaseGetAllCounterTest() {

    @Test
    fun successLoadRecordsWithOneElement() = runTest {

        //Given
        val listCounterInsects = listOf(mockCounterRecordInsectModel)
        val listInsectModel = listOf(mockInsectModel)
        val listGeoLocationModel = listOf(mockGeoLocationModel)

        every { mockCounterRecordInsectLocalDatasource.getAll() } returns flow {
            emit(listCounterInsects)
        }

        every { mockInsectLocalDatasource.getListInsects() } returns flow {
            emit(listInsectModel)
        }

        every { mockGeoLocationLocalDatasource.getAllGeoLocations() } returns flow {
            emit(listGeoLocationModel)
        }

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
        verify(exactly = 1) { mockCounterRecordInsectLocalDatasource.getAll() }
        verify(exactly = 1) { mockInsectLocalDatasource.getListInsects() }
        verify(exactly = 1) { mockGeoLocationLocalDatasource.getAllGeoLocations() }
        verify(exactly = 2) { mockInsectModel.id }
        verify(exactly = 2) { mockGeoLocationModel.id }
        verify(exactly = 1) { mockCounterRecordInsectModel.insect }
        verify(exactly = 1) { mockCounterRecordInsectModel.geoLocation }
    }

    @Test
    fun successLoadRecordsWithZeroElement() = runTest {

        //Given
        val listCounterInsects = emptyList<CounterRecordInsectModel>()
        val listInsectModel = emptyList<InsectModel>()
        val listGeoLocationModel = emptyList<GeoLocationModel>()

        every { mockCounterRecordInsectLocalDatasource.getAll() } returns flow {
            emit(listCounterInsects)
        }

        every { mockInsectLocalDatasource.getListInsects() } returns flow {
            emit(listInsectModel)
        }

        every { mockGeoLocationLocalDatasource.getAllGeoLocations() } returns flow {
            emit(listGeoLocationModel)
        }

        //when
        getAllCounter
            .invoke()
            .collect{
                Assert.assertEquals(0, it.size)
            }

        //then
        verify(exactly = 1) { mockCounterRecordInsectLocalDatasource.getAll() }
        verify(exactly = 1) { mockInsectLocalDatasource.getListInsects() }
        verify(exactly = 1) { mockGeoLocationLocalDatasource.getAllGeoLocations() }
    }

}