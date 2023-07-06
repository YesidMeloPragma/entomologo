package com.pragma.entomologo.unittest.logic.datasources.insectDatasource.imageDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class LoadImageTest : BaseInsectImageLocalDatasourceTest() {

    @Test
    fun successLoadImageTest() = runTest {
        //Given
        val pathImage = "Image"
        coEvery { mockImageAppGallery.getImageStringBase64(path = any()) } returns flow { emit(pathImage) }

        //when
        insectImageLocalDatasource
            .loadImageInsect(path = pathImage)
            .collect{
                Assert.assertEquals(pathImage, it)
            }

        //then
        coVerify(exactly = 1) { mockImageAppGallery.getImageStringBase64(path = any()) }
    }
    
}