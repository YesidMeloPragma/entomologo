package com.pragma.entomologo.unittest.logic.datasources.geoLocationDatasource.mapper.geoLocationEntityMapper

import com.pragma.entomologo.logic.datasources.geoLocationDatasource.mappers.convertToListGeoLocationModel
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ConvertListEntitiesToListModelsTest : BaseGeoLocationEntityMapperTest() {

    @Test
    fun successConvertListEntitiesToListModelsTest() = runTest {

        //Given
        val listEntities = listOf(mockGeoLocationEntity)
        every { mockGeoLocationEntity.id } answers  { 1.toLong() }
        every { mockGeoLocationEntity.lat } answers  { 1.toDouble() }
        every { mockGeoLocationEntity.lng } answers  { 1.toDouble() }

        //when
        val listModels = listEntities.convertToListGeoLocationModel()
        Assert.assertEquals(listEntities.size, listModels.size)

        val model = listModels.first()
        Assert.assertEquals(mockGeoLocationEntity.id, model.id)
        Assert.assertEquals(mockGeoLocationEntity.lat, model.lat, 0.0)
        Assert.assertEquals(mockGeoLocationEntity.lng, model.lng, 0.0)

        //then
        verify(exactly = 2) { mockGeoLocationEntity.id }
        verify(exactly = 2) { mockGeoLocationEntity.lat }
        verify(exactly = 2) { mockGeoLocationEntity.lng }
    }
}