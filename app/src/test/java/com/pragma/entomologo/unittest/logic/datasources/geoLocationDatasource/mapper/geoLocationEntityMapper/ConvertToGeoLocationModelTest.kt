package com.pragma.entomologo.unittest.logic.datasources.geoLocationDatasource.mapper.geoLocationEntityMapper

import com.pragma.entomologo.logic.datasources.geoLocationDatasource.mappers.convertToGeoLocationEntity
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class ConvertToGeoLocationModelTest : BaseGeoLocationEntityMapperTest() {

    @Test
    fun successconvertToGeoLocationModelTest() = runTest {

        //Given
        every { mockGeoLocationEntity.id } answers  { 1.toLong() }
        every { mockGeoLocationEntity.lat } answers  { 1.toDouble() }
        every { mockGeoLocationEntity.lng } answers  { 1.toDouble() }

        //when
        val model = mockGeoLocationEntity.convertToGeoLocationEntity()
        Assert.assertEquals(mockGeoLocationEntity.id, model.id)
        Assert.assertEquals(mockGeoLocationEntity.lat, model.lat, 0.0)
        Assert.assertEquals(mockGeoLocationEntity.lng, model.lng, 0.0)

        //then
        verify(exactly = 2) { mockGeoLocationEntity.id }
        verify(exactly = 2) { mockGeoLocationEntity.lat }
        verify(exactly = 2) { mockGeoLocationEntity.lng }
    }
}