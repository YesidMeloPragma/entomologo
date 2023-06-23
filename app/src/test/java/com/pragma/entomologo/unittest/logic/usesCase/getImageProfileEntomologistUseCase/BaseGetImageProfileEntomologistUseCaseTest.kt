package com.pragma.entomologo.unittest.logic.usesCase.getImageProfileEntomologistUseCase

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource.EntomologistImageDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.sharedPreferencesDatasource.EntomologistSPDatasource
import com.pragma.entomologo.logic.models.EntomologistModel
import com.pragma.entomologo.logic.usesCase.getImageProfileEntomologistUseCase.GetImageProfileEntomologistUseCase
import com.pragma.entomologo.logic.usesCase.getImageProfileEntomologistUseCase.GetImageProfileEntomologistUseCaseImpl
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseGetImageProfileEntomologistUseCaseTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var mockEntomologistImageDatasource : EntomologistImageDatasource

    @RelaxedMockK
    lateinit var mockEntomologistSPDatasource: EntomologistSPDatasource

    @RelaxedMockK
    lateinit var mockEntomologistModel: EntomologistModel

    protected lateinit var getImageProfileEntomologistUseCase: GetImageProfileEntomologistUseCase

    @Before
    fun setUp() {
        getImageProfileEntomologistUseCase = GetImageProfileEntomologistUseCaseImpl(
            entomologistImageDatasource = mockEntomologistImageDatasource,
            entomologistSPDatasource = mockEntomologistSPDatasource
        )
    }
}