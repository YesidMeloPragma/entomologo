package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.imageDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoadImageProfileTest : BaseEntomologistImageDatasourceTest() {

    @Test
    fun successLoadImage() = runTest {

        //Given
        val image = "Image"
        val path = "path"
        coEvery { mockImageAppGallery.getImageStringBase64(path = any()) } returns image

        //when
        val result = entomologistImageDatasource.loadImageProfile(path = path)
        Assert.assertEquals(image, result)

        //then
        coVerify(exactly = 1) { mockImageAppGallery.getImageStringBase64(path = any()) }
    }

    @Test
    fun successLoadImageNull() = runTest {

        //Given
        val path = "path"
        coEvery { mockImageAppGallery.getImageStringBase64(path = any()) } returns null

        //when
        val response  = entomologistImageDatasource.loadImageProfile(path = path)
        Assert.assertEquals(null, response)

        //then
        coVerify(exactly = 1) { mockImageAppGallery.getImageStringBase64(path = any()) }
    }
}