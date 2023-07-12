package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.sharedPreferencesDatasource

import com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource.ElementsEntomologistSPDatasource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CheckEnumElementsSharedPreferencesEntomologistTest : BaseEntomologistSPDatasourceTest() {

    @Test
    fun successCheckEnumElementsSharedPreferencesEntomologistTest() = runTest {

        //Given


        //when
        Assert.assertEquals("CurrentEntomologist", ElementsEntomologistSPDatasource.CURRENT_ENTOMOLOGIST.getKey())

        //then

    }
}