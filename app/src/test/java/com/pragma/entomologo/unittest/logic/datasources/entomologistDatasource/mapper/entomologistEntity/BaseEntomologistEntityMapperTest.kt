package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.mapper.entomologistEntity

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.sources.database.entities.EntomologistEntity
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import org.junit.Rule

abstract class BaseEntomologistEntityMapperTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var mockEntomologistEntity: EntomologistEntity

}