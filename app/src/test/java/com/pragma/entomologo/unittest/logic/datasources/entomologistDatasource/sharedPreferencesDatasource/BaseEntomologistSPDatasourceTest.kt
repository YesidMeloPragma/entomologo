package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.sharedPreferencesDatasource

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource.EntomologistSPDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource.EntomologistSPDatasourceImpl
import com.pragma.entomologo.logic.models.EntomologistModel
import com.pragma.entomologo.sources.sharedPreferences.CustomSharedPreferences
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseEntomologistSPDatasourceTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var mockCustomSharedPreferences: CustomSharedPreferences

    @RelaxedMockK
    lateinit var mockEntomologistModel: EntomologistModel

    protected lateinit var entomologistSPDatasource: EntomologistSPDatasource

    @Before
    fun setUp() {
        entomologistSPDatasource = EntomologistSPDatasourceImpl(
            customSharedPreferences = mockCustomSharedPreferences
        )
    }
}