package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InsertTest : BaseEntomologistLocalDatasourceTest() {

    @Test
    fun successInsertTest() = runTest {

        //Given
        val listIds = longArrayOf(1,2)
        coEvery { mockEntomologistDao.insertElement(element = anyVararg()) } answers { listIds }

        //when
        val result = entomologistLocalDatasource.insert(entomologistModel = mockEntomologistModel)
        Assert.assertEquals(listIds.size, result.size)
        Assert.assertEquals(listIds, result)

        //then
        coVerify { mockEntomologistDao.insertElement(element = anyVararg()) }

    }
}