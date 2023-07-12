package com.pragma.entomologo.unittest.logic.datasources.geoLocationDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InsertGeoLocationTest : BaseGeoLocationLocalDatasourceTest() {

    @Test
    fun successInsertGeoLocationTest() = runTest {

        //Given
        coEvery { mockGeoLocationDao.insertElement(any()) } returns 1

        //when
        val response = geoLocationLocalDatasource.insertGeoLocation(geoLocationModel = mockGeoLocationModel)
        Assert.assertEquals(1, response.size)

        //then
        coVerify(exactly = 1) { mockGeoLocationDao.insertElement(any()) }
    }
}