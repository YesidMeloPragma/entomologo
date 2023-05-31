package com.pragma.entomologo.unittest.logic.usesCase.isEntomologistRegisteredUseCase

import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InvokeTest : BaseIsEntomologistRegisteredUseCaseTest() {

    @Test
    fun notRegisteredEntomologist() = runTest {

        //Given
        every { mockEntomologistSPDatasource.getCurrentEntomologist() } returns flow {
            emit(null)
        }

        //when
            isEntomologistRegisteredUseCase
            .invoke()
            .collect {
                Assert.assertEquals(false, it)
            }

        //then
        verify (exactly = 1) { mockEntomologistSPDatasource.getCurrentEntomologist() }
    }

    @Test
    fun registeredEntomologist() = runTest {

        //Given
        every { mockEntomologistSPDatasource.getCurrentEntomologist() } returns flow {
            emit(mockEntomologistModel)
        }

        //when
        isEntomologistRegisteredUseCase
            .invoke()
            .collect {
                Assert.assertEquals(true, it)
            }

        //then
        verify (exactly = 1) { mockEntomologistSPDatasource.getCurrentEntomologist() }
    }
}