package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.mapper.entomologistModel

import com.pragma.entomologo.logic.datasources.entomologistDatasource.mapper.convertToEntomologistEntity
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ConvertToEntomologistTest : BaseConvertToEntomologistEntityTest() {

    @Test
    fun convertToEntomologistTest() = runTest {

        //Given
        every { mockEntomologistModel.id } returns 1
        every { mockEntomologistModel.name } returns "Name"
        every { mockEntomologistModel.urlPhoto } returns "Url"

        //when
        val entity = mockEntomologistModel.convertToEntomologistEntity()
        Assert.assertEquals(mockEntomologistModel.id, entity.id)
        Assert.assertEquals(mockEntomologistModel.name, entity.name)
        Assert.assertEquals(mockEntomologistModel.urlPhoto, entity.urlPhoto)

        //then
        verify(exactly = 2) { mockEntomologistModel.id }
        verify(exactly = 2) { mockEntomologistModel.name }
        verify(exactly = 2) { mockEntomologistModel.urlPhoto }
    }
}