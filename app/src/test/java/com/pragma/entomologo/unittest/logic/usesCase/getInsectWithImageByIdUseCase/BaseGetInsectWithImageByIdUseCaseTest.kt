package com.pragma.entomologo.unittest.logic.usesCase.getInsectWithImageByIdUseCase

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.insectDatasource.imageDatasource.InsectImageLocalDatasource
import com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource.InsectLocalDatasource
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.logic.usesCase.getInsectWithImageByIdUseCase.GetInsectWithImageByIdUseCase
import com.pragma.entomologo.logic.usesCase.getInsectWithImageByIdUseCase.GetInsectWithImageByIdUseCaseImpl
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseGetInsectWithImageByIdUseCaseTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var mockInsectModel : InsectModel

    @MockK
    lateinit var mockInsectLocalDatasource: InsectLocalDatasource

    @MockK
    lateinit var mockInsectImageLocalDatasource: InsectImageLocalDatasource

    protected lateinit var getInsectWithImageByIdUseCase: GetInsectWithImageByIdUseCase

    @Before
    fun setUp() {
        getInsectWithImageByIdUseCase = GetInsectWithImageByIdUseCaseImpl(
            insectImageLocalDatasource = mockInsectImageLocalDatasource,
            insectLocalDatasource = mockInsectLocalDatasource
        )
    }
}