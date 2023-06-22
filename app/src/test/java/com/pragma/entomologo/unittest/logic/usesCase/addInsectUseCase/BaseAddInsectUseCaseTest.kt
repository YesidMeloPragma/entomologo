package com.pragma.entomologo.unittest.logic.usesCase.addInsectUseCase

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource.InsectLocalDatasource
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.logic.usesCase.addInsectUseCase.AddInsectUseCase
import com.pragma.entomologo.logic.usesCase.addInsectUseCase.AddInsectUseCaseImpl
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseAddInsectUseCaseTest {

    @get:Rule
    var  rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var mockInsectLocalDatasource : InsectLocalDatasource

    @MockK
    lateinit var mockInsectModel : InsectModel

    protected lateinit var addInsectUseCase: AddInsectUseCase

    @Before
    fun setUp() {
        addInsectUseCase =  AddInsectUseCaseImpl(insectLocalDatasource = mockInsectLocalDatasource)
    }
}