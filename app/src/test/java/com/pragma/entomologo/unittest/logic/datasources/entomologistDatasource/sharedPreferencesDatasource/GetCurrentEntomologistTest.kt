package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.sharedPreferencesDatasource

import com.pragma.entomologo.logic.models.EntomologistModel
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCurrentEntomologistTest : BaseEntomologistSPDatasourceTest() {

    @Test
    fun successGetCurrentEntomologistTest() = runTest {

        //Given
        coEvery { mockCustomSharedPreferences.getElement(elementSharedPreferences = any(), classe = EntomologistModel::class.java) } returns mockEntomologistModel

        //when
        val response = entomologistSPDatasource.getCurrentEntomologist()
        Assert.assertEquals(mockEntomologistModel, response)

        //then
        coVerify(exactly = 1) { mockCustomSharedPreferences.getElement(elementSharedPreferences = any(), classe = EntomologistModel::class.java) }
    }
}