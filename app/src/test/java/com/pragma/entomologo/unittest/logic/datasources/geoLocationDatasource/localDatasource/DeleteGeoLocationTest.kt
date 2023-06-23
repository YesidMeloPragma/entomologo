package com.pragma.entomologo.unittest.logic.datasources.geoLocationDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DeleteGeoLocationTest : BaseGeoLocationLocalDatasourceTest() {

    @Test
    fun successDeleteGeoLocationTest() = runTest {

        //Given

        coEvery { mockGeoLocationDao.deleteElement(element = anyVararg()) } answers {}

        //when
        geoLocationLocalDatasource
            .deleteGeoLocation(geoLocationModel = mockGeoLocationModel)
            .collect{
                Assert.assertEquals(true, it)
            }

        //then
        coVerify(exactly = 1) { mockGeoLocationDao.deleteElement(element = anyVararg()) }

    }
}