package com.pragma.entomologo.unittest.logic.datasources.insectDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class DeleteInsectTest : BaseInsectLocalDatasourceTest() {

    @Test
    fun successDeleteInsectTest() = runTest {

        //Given
        coEvery { mockInsectDao.deleteElement(element = anyVararg()) } answers {}

        //when
        insectLocalDatasource
            .deleteInsect(insectModel = mockInsectModel)
            .collect {
                assert(it)
            }

        //then
        coVerify(exactly = 1) { mockInsectDao.deleteElement(element = anyVararg()) }
    }
}