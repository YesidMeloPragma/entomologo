package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.mapper.entomologistEntity

import com.pragma.entomologo.logic.datasources.entomologistDatasource.mapper.convertToListEntomologistModel
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ListEntityConvertToListModel : BaseEntomologistEntityMapperTest() {

    @Test
    fun listEntityConvertToListModel() = runTest {

        //Given
        val list = listOf(mockEntomologistEntity)
        every { mockEntomologistEntity.id } returns 1
        every { mockEntomologistEntity.name } returns "Name"
        every { mockEntomologistEntity.urlPhoto } returns "Url"

        //when
        val listModel = list.convertToListEntomologistModel()
        Assert.assertEquals(list.size, listModel.size)

        val model = listModel.first()
        Assert.assertEquals(mockEntomologistEntity.id, model.id)
        Assert.assertEquals(mockEntomologistEntity.name, model.name)
        Assert.assertEquals(mockEntomologistEntity.urlPhoto, model.urlPhoto)

        //then
        verify(exactly = 2) { mockEntomologistEntity.id }
        verify(exactly = 2) { mockEntomologistEntity.name }
        verify(exactly = 2) { mockEntomologistEntity.urlPhoto }
    }
}