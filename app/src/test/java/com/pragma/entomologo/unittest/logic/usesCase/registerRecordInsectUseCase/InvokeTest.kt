package com.pragma.entomologo.unittest.logic.usesCase.registerRecordInsectUseCase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

//file name InvokeTest

@OptIn(ExperimentalCoroutinesApi::class)
class InvokeTest : BaseRegisterRecordInsectUseCaseTest() {

    @Test
    fun successInvokeTest() = runTest {

        //Given
        val longArrayTest = longArrayOf(1,2,3,4,5)
        every { mockCounterRecordInsectModel.geoLocation } answers { mockGeoLocationModel }
        coEvery { mockGeoLocationLocalDatasource.insertGeoLocation(geoLocationModel = any())} returns longArrayTest
        coEvery { mockCounterRecordInsectLocalDatasource.insert(counterRecordInsectModel = any()) } returns longArrayTest

        //when
        registerRecordInsectUseCase
            .invoke(counterRecordInsectModel = mockCounterRecordInsectModel)
            .collect {
                assert(it)
            }

        //then
        coVerify(exactly = 1) { mockGeoLocationLocalDatasource.insertGeoLocation(geoLocationModel = any())}
        coVerify(exactly = 1) { mockCounterRecordInsectLocalDatasource.insert(counterRecordInsectModel = any()) }
        verify(exactly = 2) { mockCounterRecordInsectModel.geoLocation }

    }
}