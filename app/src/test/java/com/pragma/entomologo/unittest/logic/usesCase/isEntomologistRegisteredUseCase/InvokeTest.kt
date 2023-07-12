package com.pragma.entomologo.unittest.logic.usesCase.isEntomologistRegisteredUseCase

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InvokeTest : BaseIsEntomologistRegisteredUseCaseTest() {

    @Test
    fun notRegisteredEntomologist() = runTest {

        //Given
        coEvery { mockEntomologistSPDatasource.getCurrentEntomologist() } returns null

        //when
            isEntomologistRegisteredUseCase
            .invoke()
            .collect {
                Assert.assertEquals(false, it)
            }

        //then
        coVerify (exactly = 1) { mockEntomologistSPDatasource.getCurrentEntomologist() }
    }

    @Test
    fun registeredEntomologist() = runTest {

        //Given
        coEvery { mockEntomologistSPDatasource.getCurrentEntomologist() } returns mockEntomologistModel

        //when
        isEntomologistRegisteredUseCase
            .invoke()
            .collect {
                Assert.assertEquals(true, it)
            }

        //then
        coVerify (exactly = 1) { mockEntomologistSPDatasource.getCurrentEntomologist() }
    }
}