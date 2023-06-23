package com.pragma.entomologo.unittest.logic.datasources.geoLocationDatasource.mapper.geoLocationModelMapper

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.models.GeoLocationModel
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import org.junit.Rule

abstract class BaseGeoLocationEntityMapperTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var mockGeoLocationModel: GeoLocationModel
}