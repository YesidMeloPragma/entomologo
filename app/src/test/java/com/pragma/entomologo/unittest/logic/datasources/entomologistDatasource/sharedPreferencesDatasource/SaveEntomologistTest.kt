package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.sharedPreferencesDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class SaveEntomologistTest : BaseEntomologistSPDatasourceTest() {

    @Test
    fun successSaveEntomologistTest() = runTest {

        //Given
        coEvery { mockCustomSharedPreferences.saveElement(elementSharedPreferences = any(), element = mockEntomologistModel)} returns true

        //when
        entomologistSPDatasource.saveEntomologist(entomologistModel = mockEntomologistModel)

        //then
        coVerify(exactly = 1) { mockCustomSharedPreferences.saveElement(elementSharedPreferences = any(), element = mockEntomologistModel)}

    }
}