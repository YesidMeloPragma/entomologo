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
    fun insertOneElementReturnsTwoIDs() = runTest {

        //Given
        val listIDs = longArrayOf(1,2)
        coEvery { mockCounterRecordDao.insertElement(element = anyVararg()) } answers  { listIDs }

        //when
        counterRecordInsectLocalDatasource
            .insert(counterRecordInsectModel = mockCounterRecordInsectModel)
            .collect {
                Assert.assertEquals(listIDs.size, it.size)
            }

        //then
        coVerify(exactly = 1) { mockCounterRecordDao.insertElement(element = anyVararg()) }

    }

    @Test
    fun insertOneElementReturnsOneIDs() = runTest {

        //Given
        val listIDs = longArrayOf(1)
        coEvery { mockCounterRecordDao.insertElement(element = anyVararg()) } answers  { listIDs }

        //when
        counterRecordInsectLocalDatasource
            .insert(counterRecordInsectModel = mockCounterRecordInsectModel)
            .collect {
                Assert.assertEquals(listIDs.size, it.size)
            }

        //then
        coVerify(exactly = 1) { mockCounterRecordDao.insertElement(element = anyVararg()) }

    }
}