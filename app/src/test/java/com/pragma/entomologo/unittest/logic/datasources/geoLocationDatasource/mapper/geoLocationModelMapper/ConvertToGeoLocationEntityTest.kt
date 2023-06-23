package com.pragma.entomologo.unittest.logic.datasources.geoLocationDatasource.mapper.geoLocationModelMapper

import com.pragma.entomologo.logic.datasources.geoLocationDatasource.mappers.convertToGeoLocationEntity
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ConvertToGeoLocationEntityTest : BaseGeoLocationEntityMapperTest() {

    @Test
    fun successConvertToGeoLocationEntityTest() = runTest {

        //Given
        every { mockGeoLocationModel.id } answers { 1.toLong() }
        every { mockGeoLocationModel.lat } answers { 0.0 }
        every { mockGeoLocationModel.lng } answers { 0.0 }

        //when
        val entity = mockGeoLocationModel.convertToGeoLocationEntity()
        Assert.assertEquals(mockGeoLocationModel.id, entity.id)
        Assert.assertEquals(mockGeoLocationModel.lat, entity.lat, 0.0)
        Assert.assertEquals(mockGeoLocationModel.lng, entity.lng, 0.0)

        //then
        verify (exactly = 2) { mockGeoLocationModel.id }
        verify (exactly = 2) { mockGeoLocationModel.lat }
        verify (exactly = 2) { mockGeoLocationModel.lng }

    }
}