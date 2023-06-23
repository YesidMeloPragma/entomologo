package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.imageDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SaveImageProfileEntomologistTest : BaseEntomologistImageDatasourceTest() {

    @Test
    fun successSaveImage() = runTest {
        //Given
        val path = "path"
        coEvery { mockImageAppGallery.saveImage(
            stringBase64 = any(),
            path = any(),
            fileName = any()
        )} returns path

        //when
        entomologistImageDatasource
            .saveImageProfileEntomologist(
                imageBase64 = "",
                path = "",
                fileName = ""
            )

        //then
        coVerify(exactly = 1) { mockImageAppGallery.saveImage(
            stringBase64 = any(),
            path = any(),
            fileName = any()
        )}
    }
}