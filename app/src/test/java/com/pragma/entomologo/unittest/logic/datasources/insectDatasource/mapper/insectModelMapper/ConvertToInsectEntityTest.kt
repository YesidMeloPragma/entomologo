package com.pragma.entomologo.unittest.logic.datasources.insectDatasource.mapper.insectModelMapper

import com.pragma.entomologo.logic.datasources.insectDatasource.mappers.convertToInsectEntity
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ConvertToInsectEntityTest : BaseInsectModelMapperTest() {

    @Test
    fun successConvertToInsectEntityTest() = runTest {

        //Given
        every { mockInsectModel.id } answers { 1.toLong() }
        every { mockInsectModel.specieName } answers { "spaceName" }
        every { mockInsectModel.urlPhoto } answers { "urlPhoto" }
        every { mockInsectModel.moreInformation } answers { "moreInformation" }

        //when
        val entity = mockInsectModel.convertToInsectEntity()
        Assert.assertEquals(mockInsectModel.id, entity.id)
        Assert.assertEquals(mockInsectModel.specieName, entity.specieName)
        Assert.assertEquals(mockInsectModel.urlPhoto, entity.urlPhoto)
        Assert.assertEquals(mockInsectModel.moreInformation, entity.moreInformation)

        //then
        verify(exactly = 2) { mockInsectModel.id }
        verify(exactly = 2) { mockInsectModel.specieName }
        verify(exactly = 2) { mockInsectModel.urlPhoto }
        verify(exactly = 2) { mockInsectModel.moreInformation }
    }
}