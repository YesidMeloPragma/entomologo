package com.pragma.entomologo.unittest.logic.usesCase.registerRecordInsectUseCase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

//file name InvokeTest

@OptIn(ExperimentalCoroutinesApi::class)
class InvokeTest : BaseRegisterRecordInsectUseCaseTest() {

    @Test
    fun successInvokeTest() = runTest {
        /*
        //Given
        val longArrayTest = longArrayOf(1,2,3,4,5)
        every { mockInsectLocalDatasource.insertInsect(insectModel = any()) } returns flow {
            emit(longArrayTest)
        }

        //when
        addInsectUseCase
            .invoke(insectModel = mockInsectModel)
            .collect {
                Assert.assertEquals(longArrayTest.first(), it)
            }

        //then
        verify (exactly = 1) { mockInsectLocalDatasource.insertInsect(insectModel = any()) }
        */
    }
}