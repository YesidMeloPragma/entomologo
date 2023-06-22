package com.pragma.entomologo.unittest.logic.usesCase.registerEntomologistUseCase

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource.EntomologistImageDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.localDatasource.EntomologistLocalDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource.EntomologistSPDatasource
import com.pragma.entomologo.logic.models.EntomologistModel
import com.pragma.entomologo.logic.usesCase.registerEntomologistUseCase.RegisterEntomologistUseCase
import com.pragma.entomologo.logic.usesCase.registerEntomologistUseCase.RegisterEntomologistUseCaseImpl
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseRegisterEntomologistUseCaseTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var mockEntomologistModel : EntomologistModel
    @MockK
    lateinit var mockEntomologistLocalDatasource: EntomologistLocalDatasource
    @MockK
    lateinit var mockEntomologistImageDatasource: EntomologistImageDatasource
    @MockK
    lateinit var mockEntomologistSPDatasource: EntomologistSPDatasource

    protected lateinit var registerEntomologistUseCase: RegisterEntomologistUseCase

    @Before
    fun setUp() {
        registerEntomologistUseCase = RegisterEntomologistUseCaseImpl(
            entomologistLocalDatasource = mockEntomologistLocalDatasource,
            entomologistImageDatasource = mockEntomologistImageDatasource,
            entomologistSPDatasource = mockEntomologistSPDatasource
        )
    }
}