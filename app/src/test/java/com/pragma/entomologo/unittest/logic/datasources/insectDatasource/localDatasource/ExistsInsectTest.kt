package com.pragma.entomologo.unittest.logic.datasources.insectDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ExistsInsectTest : BaseInsectLocalDatasourceTest() {

    @Test
    fun successExistsInsectTest() = runTest {

        //Given
        coEvery { mockInsectDao.existsInsect(nameSpecie = any()) } returns true

        //when
        val response = insectLocalDatasource.existsInsect(nameSpecie = "")
        Assert.assertEquals(true, response)

        //then
        coVerify(exactly = 1) { mockInsectDao.existsInsect(nameSpecie = any()) }
    }

    @Test
    fun successExistsInsectFalseTest() = runTest {

        //Given
        coEvery { mockInsectDao.existsInsect(nameSpecie = any()) } returns false

        //when
        val response = insectLocalDatasource.existsInsect(nameSpecie = "")
        Assert.assertEquals(false, response)

        //then
        coVerify(exactly = 1) { mockInsectDao.existsInsect(nameSpecie = any()) }
    }
}