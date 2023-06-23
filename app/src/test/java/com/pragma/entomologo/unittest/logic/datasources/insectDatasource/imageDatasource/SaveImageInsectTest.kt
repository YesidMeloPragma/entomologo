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
class SaveImageInsectTest : BaseInsectImageLocalDatasourceTest() {

    @Test
    fun successSaveImageInsectTest() = runTest {

        //Given
        val pathImage = "path"
        coEvery { mockImageAppGallery.saveImage(stringBase64 = any(), path = any(), fileName = any()) } returns pathImage

        //when
        val result = insectImageLocalDatasource.saveImageInsect(imageBase64 = "", path = "", fileName = "")
        Assert.assertEquals(pathImage, result)
        
        //then
        coVerify(exactly = 1) { mockImageAppGallery.saveImage(stringBase64 = any(), path = any(), fileName = any()) }
    }
}