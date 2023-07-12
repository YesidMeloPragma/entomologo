package com.pragma.entomologo.unittest.logic.datasources.insectDatasource.imageDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class LoadImageTest : BaseInsectImageLocalDatasourceTest() {

    @Test
    fun successLoadImageTest() = runTest {
        //Given
        val pathImage = "Image"
        coEvery { mockImageAppGallery.getImageStringBase64(path = any()) } returns pathImage

        //when
        val response = insectImageLocalDatasource.loadImageInsect(path = pathImage)
        Assert.assertEquals(pathImage, response)

        //then
        coVerify(exactly = 1) { mockImageAppGallery.getImageStringBase64(path = any()) }
    }
    
}