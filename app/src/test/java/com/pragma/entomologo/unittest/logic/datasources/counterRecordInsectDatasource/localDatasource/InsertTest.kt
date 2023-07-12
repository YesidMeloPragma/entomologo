package com.pragma.entomologo.unittest.logic.datasources.counterRecordInsectDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InsertTest : BaseCounterRecordInsectLocalDatasourceTest() {

    @Test
    fun insertOneElementReturnsOneIDs() = runTest {

        //Given
        coEvery { mockCounterRecordDao.insertElement(any()) } returns 1

        //when
        val list = counterRecordInsectLocalDatasource.insert(counterRecordInsectModel = mockCounterRecordInsectModel)
        Assert.assertEquals(1, list.size)

        //then
        coVerify(exactly = 0) { mockCounterRecordDao.insertElement(any()) }
    }
}