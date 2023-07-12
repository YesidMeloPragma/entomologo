package com.pragma.entomologo.unittest.logic.usesCase.getImageProfileEntomologistUseCase

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InvokeTest : BaseGetImageProfileEntomologistUseCaseTest() {

    @Test
    fun successLoadImageProfile() = runTest {

        //Given
        val image = ""
        coEvery { mockEntomologistImageDatasource.loadImageProfile(path = any()) } returns image

        coEvery { mockEntomologistSPDatasource.getCurrentEntomologist() } returns mockEntomologistModel

        //when
        getImageProfileEntomologistUseCase
            .invoke()
            .collect{
                Assert.assertEquals(image, it)
            }

        //then
        coVerify (exactly = 1) { mockEntomologistImageDatasource.loadImageProfile(path = any()) }
        coVerify (exactly = 1) { mockEntomologistSPDatasource.getCurrentEntomologist() }

    }

    @Test
    fun successLoadImageProfileNull() = runTest {

        //Given
        val image : String? = null
        coEvery { mockEntomologistImageDatasource.loadImageProfile(path = any()) } returns image

        coEvery { mockEntomologistSPDatasource.getCurrentEntomologist() } returns mockEntomologistModel

        //when
        getImageProfileEntomologistUseCase
            .invoke()
            .collect{
                Assert.assertEquals(image, it)
            }

        //then
        coVerify (exactly = 1) { mockEntomologistImageDatasource.loadImageProfile(path = any()) }
        coVerify (exactly = 1) { mockEntomologistSPDatasource.getCurrentEntomologist() }

    }


    @Test
    fun successLoadImageProfileEntomologistModelNull() = runTest {

        //Given
        val image : String? = null
        coEvery { mockEntomologistImageDatasource.loadImageProfile(path = any()) } returns image

        coEvery { mockEntomologistSPDatasource.getCurrentEntomologist() } returns null

        //when
        getImageProfileEntomologistUseCase
            .invoke()
            .collect{
                Assert.assertEquals(image, it)
            }

        //then
        coVerify (exactly = 0) { mockEntomologistImageDatasource.loadImageProfile(path = any()) }
        coVerify (exactly = 1) { mockEntomologistSPDatasource.getCurrentEntomologist() }

    }
}