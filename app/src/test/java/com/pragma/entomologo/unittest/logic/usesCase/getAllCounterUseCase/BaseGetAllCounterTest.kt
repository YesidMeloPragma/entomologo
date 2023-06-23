package com.pragma.entomologo.unittest.logic.usesCase.getAllCounterUseCase

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource.CounterRecordInsectLocalDatasource
import com.pragma.entomologo.logic.datasources.geoLocationDatasource.localDatasource.GeoLocationLocalDatasource
import com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource.InsectLocalDatasource
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import com.pragma.entomologo.logic.models.GeoLocationModel
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.logic.usesCase.getAllCountersUseCase.GetAllCountersUseCase
import com.pragma.entomologo.logic.usesCase.getAllCountersUseCase.GetAllCountersUseCaseImpl
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseGetAllCounterTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var mockCounterRecordInsectLocalDatasource : CounterRecordInsectLocalDatasource

    @MockK
    lateinit var mockInsectLocalDatasource : InsectLocalDatasource

    @MockK
    lateinit var mockGeoLocationLocalDatasource: GeoLocationLocalDatasource

    @RelaxedMockK
    lateinit var mockCounterRecordInsectModel: CounterRecordInsectModel

    @RelaxedMockK
    lateinit var mockInsectModel :  InsectModel

    @RelaxedMockK
    lateinit var mockGeoLocationModel: GeoLocationModel

    protected lateinit var getAllCounter: GetAllCountersUseCase

    @Before
    fun setUp() {
        getAllCounter = GetAllCountersUseCaseImpl(
            counterRecordInsectLocalDatasource = mockCounterRecordInsectLocalDatasource,
            insectLocalDatasource = mockInsectLocalDatasource,
            geoLocationLocalDatasource = mockGeoLocationLocalDatasource
        )
    }
}