package com.pragma.entomologo.unittest.logic.usesCase.getInsectWithImageByIdUseCase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InvokeTest : BaseGetInsectWithImageByIdUseCaseTest() {

    @Test
    fun successInvokeTest() = runTest {
        //Given
        val image = "image"
        val id = 1.toLong()
        every { mockInsectModel.urlPhoto } answers { image }
        coEvery { mockInsectLocalDatasource.getInsectModelById(insectId = any()) } returns mockInsectModel
        coEvery { mockInsectImageLocalDatasource.loadImageInsect(path = any()) } returns image

        //when
        getInsectWithImageByIdUseCase
            .invoke(insectId = id)
            .collect {
                Assert.assertEquals(mockInsectModel, it.first)
                Assert.assertEquals(image, it.second)
            }

        //then
        verify(exactly = 1) { mockInsectModel.urlPhoto }
        coVerify(exactly = 1) { mockInsectLocalDatasource.getInsectModelById(insectId = any()) }
        coVerify(exactly = 1) { mockInsectImageLocalDatasource.loadImageInsect(path = any()) }
    }
}