package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.imageDatasource

import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
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
        every { mockImageAppGallery.getImageStringBase64(path = any()) } returns flow {
            emit(image)
        }

        //when
        entomologistImageDatasource
            .loadImageProfile(path = path)
            .collect{
                Assert.assertEquals(image, it)
            }

        //then
        verify(exactly = 1) { mockImageAppGallery.getImageStringBase64(path = any()) }
    }

    @Test
    fun successLoadImageNull() = runTest {

        //Given
        val path = "path"
        every { mockImageAppGallery.getImageStringBase64(path = any()) } returns flow {
            emit(null)
        }

        //when
        entomologistImageDatasource
            .loadImageProfile(path = path)
            .collect{
                Assert.assertEquals(null, it)
            }

        //then
        verify(exactly = 1) { mockImageAppGallery.getImageStringBase64(path = any()) }
    }
}