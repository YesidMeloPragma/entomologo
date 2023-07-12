package com.pragma.entomologo.unittest.logic.datasources.insectDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetInsectIdByNameTest : BaseInsectLocalDatasourceTest() {

    @Test
    fun successGetInsectIdByNameTest() = runTest {

        //Given
        val id = 1.toLong()
        coEvery { mockInsectDao.getInsectIdByName(nameSpecie = any()) } returns id

        //when
        val response = insectLocalDatasource.getInsectIdByName(nameSpecie = "")
        Assert.assertEquals(id, response)

        //then
        coVerify(exactly = 1) { mockInsectDao.getInsectIdByName(nameSpecie = any()) }
        
    }
}