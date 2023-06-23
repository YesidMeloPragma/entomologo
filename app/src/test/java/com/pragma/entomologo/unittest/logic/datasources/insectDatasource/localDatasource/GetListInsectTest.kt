package com.pragma.entomologo.unittest.logic.datasources.insectDatasource.localDatasource

import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetListInsectTest : BaseInsectLocalDatasourceTest() {

    @Test
    fun successGetListInsectTest() = runTest {

        //Given
        val listEntities = listOf(mockInsectEntity)
        every { mockInsectDao.getAllInsects() } returns flow {
            emit(listEntities)
        }

        //when
        insectLocalDatasource
            .getListInsects()
            .collect {
                Assert.assertEquals(listEntities.size, it.size)
            }

        //then
        verify(exactly = 1) { mockInsectDao.getAllInsects() }
    }
}