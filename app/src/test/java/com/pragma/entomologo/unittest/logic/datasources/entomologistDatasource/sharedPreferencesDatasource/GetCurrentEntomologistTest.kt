package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.sharedPreferencesDatasource

import com.pragma.entomologo.logic.models.EntomologistModel
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCurrentEntomologistTest : BaseEntomologistSPDatasourceTest() {

    @Test
    fun successGetCurrentEntomologistTest() = runTest {

        //Given
        every { mockCustomSharedPreferences.getElement(elementSharedPreferences = any(), classe = EntomologistModel::class.java) } returns flow {
            emit(mockEntomologistModel)
        }

        //when
        entomologistSPDatasource
            .getCurrentEntomologist()
            .collect{
                Assert.assertEquals(mockEntomologistModel, it)
            }

        //then
        verify(exactly = 1) { mockCustomSharedPreferences.getElement(elementSharedPreferences = any(), classe = EntomologistModel::class.java) }
    }
}