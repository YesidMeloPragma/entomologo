package com.pragma.entomologo.unittest.logic.datasources.counterRecordInsectDatasource.localDatasource

import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetAllTest : BaseCounterRecordInsectLocalDatasourceTest() {

    @Test
    fun successGetAllWithOneElement() = runTest {

        //Given
        val list = listOf(mockCounterRecordInsectEntity)

        every { mockCounterRecordDao.getAll() } returns flow {
            emit(list)
        }
        
        //when
        counterRecordInsectLocalDatasource
            .getAll()
            .collect{
                Assert.assertEquals(list.size, it.count())
            }

        //then
        verify(exactly = 1) { mockCounterRecordDao.getAll() }
    }
}