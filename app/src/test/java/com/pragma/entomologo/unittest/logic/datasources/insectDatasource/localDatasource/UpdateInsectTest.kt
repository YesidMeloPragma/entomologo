package com.pragma.entomologo.unittest.logic.datasources.insectDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UpdateInsectTest : BaseInsectLocalDatasourceTest() {

    @Test
    fun successUpdateInsectTest() = runTest {

        //Given
        coEvery { mockInsectDao.updateElement(element = any()) } answers {}

        //when
        insectLocalDatasource
            .updateInsect(insectModel = mockInsectModel)
            .collect{
                assert(it)
            }

        //then
        coVerify(exactly = 1) { mockInsectDao.updateElement(element = any()) }

    }
}