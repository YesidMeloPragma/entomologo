package com.pragma.entomologo.unittest.logic.usesCase.getImageProfileEntomologistUseCase

import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InvokeTest : BaseGetImageProfileEntomologistUseCaseTest() {

    @Test
    fun successLoadImageProfile() = runTest {

        //Given
        val image = ""
        every { mockEntomologistImageDatasource.loadImageProfile(path = any()) } returns flow {
            emit(image)
        }

        every { mockEntomologistSPDatasource.getCurrentEntomologist() } returns flow {
            emit(mockEntomologistModel)
        }

        //when
        getImageProfileEntomologistUseCase
            .invoke()
            .collect{
                Assert.assertEquals(image, it)
            }

        //then
        verify (exactly = 1) { mockEntomologistImageDatasource.loadImageProfile(path = any()) }
        verify (exactly = 1) { mockEntomologistSPDatasource.getCurrentEntomologist() }

    }

    @Test
    fun successLoadImageProfileNull() = runTest {

        //Given
        val image : String? = null
        every { mockEntomologistImageDatasource.loadImageProfile(path = any()) } returns flow {
            emit(image)
        }

        every { mockEntomologistSPDatasource.getCurrentEntomologist() } returns flow {
            emit(mockEntomologistModel)
        }

        //when
        getImageProfileEntomologistUseCase
            .invoke()
            .collect{
                Assert.assertEquals(image, it)
            }

        //then
        verify (exactly = 1) { mockEntomologistImageDatasource.loadImageProfile(path = any()) }
        verify (exactly = 1) { mockEntomologistSPDatasource.getCurrentEntomologist() }

    }


    @Test
    fun successLoadImageProfileEntomologistModelNull() = runTest {

        //Given
        val image : String? = null
        every { mockEntomologistImageDatasource.loadImageProfile(path = any()) } returns flow {
            emit(image)
        }

        every { mockEntomologistSPDatasource.getCurrentEntomologist() } returns flow {
            emit(null)
        }

        //when
        getImageProfileEntomologistUseCase
            .invoke()
            .collect{
                Assert.assertEquals(image, it)
            }

        //then
        verify (exactly = 0) { mockEntomologistImageDatasource.loadImageProfile(path = any()) }
        verify (exactly = 1) { mockEntomologistSPDatasource.getCurrentEntomologist() }

    }
}