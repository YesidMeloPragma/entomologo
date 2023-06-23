package com.pragma.entomologo.unittest.logic.datasources.counterRecordInsectDatasource.localDatasource

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource.CounterRecordInsectLocalDatasource
import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.localDatasource.CounterRecordInsectLocalDatasourceImpl
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import com.pragma.entomologo.logic.models.GeoLocationModel
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.sources.database.dao.CounterRecordInsectDao
import com.pragma.entomologo.sources.database.entities.CounterRecordInsectEntity
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseCounterRecordInsectLocalDatasourceTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var mockCounterRecordDao : CounterRecordInsectDao

    @RelaxedMockK
    lateinit var mockCounterRecordInsectModel: CounterRecordInsectModel

    @RelaxedMockK
    lateinit var mockCounterRecordInsectEntity: CounterRecordInsectEntity

    @RelaxedMockK
    lateinit var mockInsectModel: InsectModel

    @RelaxedMockK
    lateinit var mockGeoLocationModel: GeoLocationModel

    protected lateinit var counterRecordInsectLocalDatasource: CounterRecordInsectLocalDatasource

    @Before
    fun setUp() {
        counterRecordInsectLocalDatasource = CounterRecordInsectLocalDatasourceImpl(
            counterRecordInsectDao = mockCounterRecordDao
        )
    }
}