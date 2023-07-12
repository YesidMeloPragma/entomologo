package com.pragma.entomologo.unittest.logic.usesCase.registerRecordInsectUseCase

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource.CounterRecordInsectLocalDatasource
import com.pragma.entomologo.logic.datasources.geoLocationDatasource.localDatasource.GeoLocationLocalDatasource
import com.pragma.entomologo.logic.usesCase.registerRecordInsectUseCase.RegisterRecordInsectUseCase
import com.pragma.entomologo.logic.usesCase.registerRecordInsectUseCase.RegisterRecordInsectUseCaseImpl
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseRegisterRecordInsectUseCaseTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var mockCounterRecordInsectLocalDatasource: CounterRecordInsectLocalDatasource

    @RelaxedMockK
    lateinit var mockGeoLocationLocalDatasource: GeoLocationLocalDatasource


    protected lateinit var registerRecordInsectUseCase: RegisterRecordInsectUseCase

    @Before
    fun setUp() {
        registerRecordInsectUseCase = RegisterRecordInsectUseCaseImpl(
            counterRecordInsectLocalDatasource = mockCounterRecordInsectLocalDatasource,
            geoLocationLocalDatasource = mockGeoLocationLocalDatasource
        )
    }
}