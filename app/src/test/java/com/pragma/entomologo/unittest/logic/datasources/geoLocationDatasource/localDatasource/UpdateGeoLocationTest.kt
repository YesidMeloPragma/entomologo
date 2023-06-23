package com.pragma.entomologo.unittest.logic.datasources.geoLocationDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UpdateGeoLocationTest : BaseGeoLocationLocalDatasourceTest() {

    @Test
    fun successUpdateGeoLocationTest() = runTest {

        //Given
        coEvery { mockGeoLocationDao.updateElement(element = any()) } answers {}

        //when
        geoLocationLocalDatasource
            .updateGeoLocation(geoLocationModel = mockGeoLocationModel)
            .collect {
                assert(it)
            }

        //then
        coVerify(exactly = 1) { mockGeoLocationDao.updateElement(element = any()) }

    }
}