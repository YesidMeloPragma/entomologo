package com.pragma.entomologo.unittest.logic.usesCase.getAllInsectsUseCase

import com.pragma.entomologo.logic.models.InsectModel
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InvokeTest : BaseGetAllInsectsUseCaseTest() {

    @Test
    fun successLoadListInsectWithOneElement() = runTest {

        //Given
        val listInsectMock = listOf(mockInsectModel)
        every { mockInsectLocalDatasource.getListInsects() } returns flow {
            emit(listInsectMock)
        }

        //when
        getAllInsectsUseCase
            .invoke()
            .collect {
                Assert.assertEquals(1, it.size)
            }

        //then
        verify (exactly = 1) { mockInsectLocalDatasource.getListInsects() }
    }

    @Test
    fun successLoadListInsectWithZeroElement() = runTest {

        //Given
        val listInsectMock = emptyList<InsectModel>()
        every { mockInsectLocalDatasource.getListInsects() } returns flow {
            emit(listInsectMock)
        }

        //when
        getAllInsectsUseCase
            .invoke()
            .collect {
                Assert.assertEquals(0, it.size)
            }

        //then
        verify (exactly = 1) { mockInsectLocalDatasource.getListInsects() }
    }
}