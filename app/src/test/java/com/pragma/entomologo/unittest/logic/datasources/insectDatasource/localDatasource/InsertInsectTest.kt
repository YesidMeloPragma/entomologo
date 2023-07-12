package com.pragma.entomologo.unittest.logic.datasources.insectDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class InsertInsectTest : BaseInsectLocalDatasourceTest() {

    @Test
    fun successInsertInsectTest() = runTest {

        //Given
        coEvery {mockInsectDao.insertElement(any())} returns 1

        //when
        insectLocalDatasource
            .insertInsect(insectModel = mockInsectModel)
            .collect{
                Assert.assertEquals(1, it.size)
            }

        //then
        coVerify (exactly = 1){mockInsectDao.insertElement(any())}

    }
}