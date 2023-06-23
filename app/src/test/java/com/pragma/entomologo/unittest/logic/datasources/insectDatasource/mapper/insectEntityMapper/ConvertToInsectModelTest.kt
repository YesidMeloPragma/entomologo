package com.pragma.entomologo.unittest.logic.datasources.insectDatasource.mapper.insectEntityMapper

import com.pragma.entomologo.logic.datasources.insectDatasource.mappers.convertToInsectModel
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class ConvertToInsectModelTest : BaseInsectEntityMapperTest() {

    @Test
    fun successConvertToModelTest() = runTest {

        //Given
        every { mockInsectEntity.id } answers { 1.toLong() }
        every { mockInsectEntity.specieName } answers { "SpecieName" }
        every { mockInsectEntity.urlPhoto } answers { "SpeciePhoto" }
        every { mockInsectEntity.id } answers { 1.toLong() }

        //when
        val model = mockInsectEntity.convertToInsectModel()
        Assert.assertEquals(mockInsectEntity.id, model.id)
        Assert.assertEquals(mockInsectEntity.specieName, model.specieName)
        Assert.assertEquals(mockInsectEntity.urlPhoto, model.urlPhoto)
        Assert.assertEquals(mockInsectEntity.moreInformation, model.moreInformation)

        //then
        verify(exactly = 2) { mockInsectEntity.id }
        verify(exactly = 2) { mockInsectEntity.specieName }
        verify(exactly = 2) { mockInsectEntity.urlPhoto }
        verify(exactly = 2) { mockInsectEntity.id }

    }
}