package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.mapper.entomologistModel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.models.EntomologistModel
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import org.junit.Rule

abstract class BaseConvertToEntomologistEntityTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var mockEntomologistModel: EntomologistModel
}