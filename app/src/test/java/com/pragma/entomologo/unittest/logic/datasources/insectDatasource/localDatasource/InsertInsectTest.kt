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
        val listIds = longArrayOf(1,2,3,4,5)
        coEvery {mockInsectDao.insertElement(element = anyVararg())} answers { listIds }

        //when
        insectLocalDatasource
            .insertInsect(insectModel = mockInsectModel)
            .collect{
                Assert.assertEquals(listIds.size, it.size)
                Assert.assertEquals(listIds, it)
            }

        //then
        coVerify (exactly = 1){mockInsectDao.insertElement(element = anyVararg())}

    }
}