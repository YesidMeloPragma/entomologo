package com.pragma.entomologo.unittest.logic.datasources.counterRecordInsectDatasource.mapper.cointerRecordInsectDetailMapper

import com.pragma.entomologo.sources.database.views.CounterRecordInsectDetailView
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import org.junit.Rule

abstract class BaseCounterRecordInsectDetailMapperTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var mockCounterRecordInsectDetailView: CounterRecordInsectDetailView
}