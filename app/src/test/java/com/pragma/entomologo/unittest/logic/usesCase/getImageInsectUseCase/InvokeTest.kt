package com.pragma.entomologo.unittest.logic.usesCase.getImageInsectUseCase

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class InvokeTest : BaseGetImageInsectUseCaseTest() {

    @Test
    fun successInvokeUrlPhotoEmptyTest() = runTest {

        //Given
        coEvery { mockInsectModel.urlPhoto } returns ""

        //when
        getImageInsectUseCase
            .invoke(insectModel = mockInsectModel)
            .collect {
                Assert.assertEquals(null, it)
            }

        //then
        coVerify(exactly = 1) { mockInsectModel.urlPhoto }

    }

    @Test
    fun successInvokeUrlPhotoTest() = runTest {

        //Given
        val image = "image"
        coEvery { mockInsectModel.urlPhoto } returns image
        coEvery { mockInsectImageLocalDatasource.loadImageInsect(path = any()) } returns image
        //when
        getImageInsectUseCase
            .invoke(insectModel = mockInsectModel)
            .collect {
                Assert.assertEquals(image, it)
            }

        //then
        coVerify(exactly = 2) { mockInsectModel.urlPhoto }
        coVerify(exactly = 1) { mockInsectImageLocalDatasource.loadImageInsect(path = any()) }

    }
}