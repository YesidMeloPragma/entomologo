package com.pragma.entomologo.unittest.logic.datasources.geoLocationDatasource.localDatasource

import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetAllGeoLocationsTest : BaseGeoLocationLocalDatasourceTest() {

    @Test
    fun successGetAllGeoLocationsTest() = runTest {
        //Given
        val listEntities = listOf(mockGeoLocationEntity)
        every { mockGeoLocationDao.getAllGeoLocations() } returns flow {
            emit(listEntities)
        }

        //when
        geoLocationLocalDatasource
            .getAllGeoLocations()
            .collect{
                Assert.assertEquals(listEntities.size, it.size)
            }

        //then
        verify(exactly = 1) { mockGeoLocationDao.getAllGeoLocations() }
    }
}