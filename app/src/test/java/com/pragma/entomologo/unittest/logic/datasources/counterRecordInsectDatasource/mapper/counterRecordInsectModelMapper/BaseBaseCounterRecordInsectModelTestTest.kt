package com.pragma.entomologo.unittest.logic.datasources.counterRecordInsectDatasource.mapper.counterRecordInsectModelMapper

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.models.GeoLocationModel
import com.pragma.entomologo.logic.models.InsectModel
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import org.junit.Rule

abstract class BaseBaseCounterRecordInsectModelTestTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var mockInsectModel: InsectModel

    @RelaxedMockK
    lateinit var mockGeoLocationModel: GeoLocationModel

}