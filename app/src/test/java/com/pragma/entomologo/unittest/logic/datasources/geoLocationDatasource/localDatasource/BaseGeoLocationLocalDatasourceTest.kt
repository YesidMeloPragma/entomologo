package com.pragma.entomologo.unittest.logic.datasources.geoLocationDatasource.localDatasource

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.geoLocationDatasource.localDatasource.GeoLocationLocalDatasource
import com.pragma.entomologo.logic.datasources.geoLocationDatasource.localDatasource.GeoLocationLocalDatasourceImpl
import com.pragma.entomologo.logic.models.GeoLocationModel
import com.pragma.entomologo.sources.database.dao.GeoLocationDao
import com.pragma.entomologo.sources.database.entities.GeoLocationEntity
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseGeoLocationLocalDatasourceTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var mockGeoLocationDao: GeoLocationDao

    @RelaxedMockK
    lateinit var mockGeoLocationModel: GeoLocationModel

    @RelaxedMockK
    lateinit var mockGeoLocationEntity: GeoLocationEntity

    protected lateinit var geoLocationLocalDatasource: GeoLocationLocalDatasource

    @Before
    fun setUp() {
        geoLocationLocalDatasource = GeoLocationLocalDatasourceImpl(
            geoLocationDao = mockGeoLocationDao
        )
    }
}