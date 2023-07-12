package com.pragma.entomologo.unittest.logic.datasources.counterRecordInsectDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetAllTest : BaseCounterRecordInsectLocalDatasourceTest() {

    @Test
    fun successGetAllWithOneElement() = runTest {

        //Given
        val list = listOf(mockCounterRecordInsectDetailView)

        coEvery { mockCounterRecordInsectDetailDao.getAllCounterRecordInsectDetail() } returns list
        //when
        val listModel = counterRecordInsectLocalDatasource.getAll()
        Assert.assertEquals(list.size, listModel.size)

        //then
        coVerify (exactly = 1) { mockCounterRecordInsectDetailDao.getAllCounterRecordInsectDetail() }
    }
}