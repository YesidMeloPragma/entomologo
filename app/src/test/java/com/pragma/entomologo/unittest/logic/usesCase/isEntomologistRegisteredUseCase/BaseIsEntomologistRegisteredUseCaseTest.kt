package com.pragma.entomologo.unittest.logic.usesCase.isEntomologistRegisteredUseCase

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource.EntomologistSPDatasource
import com.pragma.entomologo.logic.models.EntomologistModel
import com.pragma.entomologo.logic.usesCase.isEntomologistRegisteredUseCase.IsEntomologistRegisteredUseCase
import com.pragma.entomologo.logic.usesCase.isEntomologistRegisteredUseCase.IsEntomologistRegisteredUseCaseImpl
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseIsEntomologistRegisteredUseCaseTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var mockEntomologistSPDatasource: EntomologistSPDatasource

    @MockK
    lateinit var mockEntomologistModel: EntomologistModel

    protected lateinit var isEntomologistRegisteredUseCase: IsEntomologistRegisteredUseCase

    @Before
    fun setUp() {
        isEntomologistRegisteredUseCase = IsEntomologistRegisteredUseCaseImpl(entomologistSPDatasource = mockEntomologistSPDatasource)
    }
}