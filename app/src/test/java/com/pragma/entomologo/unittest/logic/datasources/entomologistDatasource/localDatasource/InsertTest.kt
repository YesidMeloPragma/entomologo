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
        coEvery { mockEntomologistDao.insertElement(any()) } returns 1

        //when
        val result = entomologistLocalDatasource.insert(entomologistModel = mockEntomologistModel)
        Assert.assertEquals(1, result.size)

        //then
        coVerify { mockEntomologistDao.insertElement(any()) }

    }
}