package com.pragma.entomologo.unittest.logic.datasources.insectDatasource.mapper.insectEntityMapper

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.sources.database.entities.InsectEntity
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import org.junit.Rule

abstract class BaseInsectEntityMapperTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var mockInsectEntity: InsectEntity

}