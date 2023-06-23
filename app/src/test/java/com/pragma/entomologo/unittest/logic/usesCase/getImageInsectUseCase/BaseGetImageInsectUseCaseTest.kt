package com.pragma.entomologo.unittest.logic.usesCase.getImageInsectUseCase

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.insectDatasource.imageDatasource.InsectImageLocalDatasource
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.logic.usesCase.getImageInsectUseCase.GetImageInsectUseCase
import com.pragma.entomologo.logic.usesCase.getImageInsectUseCase.GetImageInsectUseCaseImpl
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseGetImageInsectUseCaseTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var mockInsectModel : InsectModel

    @MockK
    lateinit var mockInsectImageLocalDatasource: InsectImageLocalDatasource

    protected lateinit var getImageInsectUseCase: GetImageInsectUseCase

    @Before
    fun setUp() {
        getImageInsectUseCase = GetImageInsectUseCaseImpl(insectImageLocalDatasource = mockInsectImageLocalDatasource)
    }
}