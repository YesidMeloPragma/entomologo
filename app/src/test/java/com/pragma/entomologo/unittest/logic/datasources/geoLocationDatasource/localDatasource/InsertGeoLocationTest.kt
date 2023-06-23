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
        val longArrayTest = longArrayOf(1,2,3,4,5)
        coEvery { mockGeoLocationDao.insertElement(element = anyVararg()) } answers { longArrayTest }

        //when
        geoLocationLocalDatasource
            .insertGeoLocation(geoLocationModel = mockGeoLocationModel)
            .collect{
                Assert.assertEquals(longArrayTest, it)
            }

        //then
        coVerify(exactly = 1) { mockGeoLocationDao.insertElement(element = anyVararg()) }
    }
}