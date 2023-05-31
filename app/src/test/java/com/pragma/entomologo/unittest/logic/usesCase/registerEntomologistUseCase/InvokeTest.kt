package com.pragma.entomologo.unittest.logic.usesCase.registerEntomologistUseCase

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InvokeTest : BaseRegisterEntomologistUseCaseTest() {

    @Test
    fun failSaveImageProfile() = runTest {

        // region Given
        coEvery {
            mockEntomologistImageDatasource.saveImageProfileEntomologist(
                path = any(),
                imageBase64 = any(),
                fileName = any()
            )
        } returns null

        //endregion

        // region when
        val result = registerEntomologistUseCase.invoke(
            bitmapBase64 = "",
            entomologistModel = mockEntomologistModel
        )
        Assert.assertEquals(false, result)
        //endregion

        //then
        coVerify (exactly = 1) {
            mockEntomologistImageDatasource.saveImageProfileEntomologist(
                path = any(),
                imageBase64 = any(),
                fileName = any()
            )
        }

    }

    @Test
    fun saveEntomologistInLocalDatasource() = runTest {

        // region Given
        val path = "path"
        val listIDs = longArrayOf(1,2,3)
        coEvery {
            mockEntomologistImageDatasource.saveImageProfileEntomologist(
                path = any(),
                imageBase64 = any(),
                fileName = any()
            )
        } returns path

        coEvery { mockEntomologistLocalDatasource.insert(entomologistModel = any()) } returns listIDs
        coEvery { mockEntomologistSPDatasource.saveEntomologist(entomologistModel = any()) } answers {}

        //endregion

        // region when
        val result = registerEntomologistUseCase.invoke(
            bitmapBase64 = "",
            entomologistModel = mockEntomologistModel
        )
        Assert.assertEquals(true, result)
        //endregion

        //then
        coVerify (exactly = 1) {
            mockEntomologistImageDatasource.saveImageProfileEntomologist(
                path = any(),
                imageBase64 = any(),
                fileName = any()
            )
        }
        coVerify (exactly = 1) { mockEntomologistLocalDatasource.insert(entomologistModel = any()) }
        coVerify (exactly = 1) { mockEntomologistSPDatasource.saveEntomologist(entomologistModel = any()) }
    }
}