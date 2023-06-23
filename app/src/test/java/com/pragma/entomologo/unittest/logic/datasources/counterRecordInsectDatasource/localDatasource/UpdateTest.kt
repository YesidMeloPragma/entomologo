package com.pragma.entomologo.unittest.logic.datasources.counterRecordInsectDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UpdateTest : BaseCounterRecordInsectLocalDatasourceTest() {

    @Test
    fun updateOneElement() = runTest {

        //Given
        coEvery { mockCounterRecordDao.updateElement(element = any()) } answers {}

        //when
        counterRecordInsectLocalDatasource
            .update(counterRecordInsectModel = mockCounterRecordInsectModel)
            .collect {
                assert(it)
            }

        //then
        coVerify(exactly = 1) { mockCounterRecordDao.updateElement(element = any()) }

    }
}