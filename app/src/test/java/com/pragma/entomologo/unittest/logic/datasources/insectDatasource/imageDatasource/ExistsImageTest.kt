package com.pragma.entomologo.unittest.logic.datasources.insectDatasource.imageDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ExistsImageTest : BaseInsectImageLocalDatasourceTest() {

    @Test
    fun successNotExistsImageTest() = runTest {
        //Given
        coEvery { mockImageAppGallery.existsImage(path = any()) } returns false

        //when
        val result = insectImageLocalDatasource.existsImage(path = "")
        assert(!result)

        //then
        coVerify(exactly = 1) { mockImageAppGallery.existsImage(path = any()) }
    }

    @Test
    fun successExistsImageTest() = runTest {
        //Given
        coEvery { mockImageAppGallery.existsImage(path = any()) } returns true

        //when
        val result = insectImageLocalDatasource.existsImage(path = "")
        assert(result)

        //then
        coVerify(exactly = 1) { mockImageAppGallery.existsImage(path = any()) }
    }
}