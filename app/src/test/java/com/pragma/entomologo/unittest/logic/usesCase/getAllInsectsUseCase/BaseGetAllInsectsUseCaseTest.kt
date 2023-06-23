package com.pragma.entomologo.unittest.logic.usesCase.getAllInsectsUseCase

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource.InsectLocalDatasource
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.logic.usesCase.getAllInsectsUseCase.GetAllInsectsUseCase
import com.pragma.entomologo.logic.usesCase.getAllInsectsUseCase.GetAllInsectsUseCaseImpl
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseGetAllInsectsUseCaseTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var mockInsectLocalDatasource : InsectLocalDatasource

    @RelaxedMockK
    lateinit var mockInsectModel: InsectModel

    protected lateinit var getAllInsectsUseCase: GetAllInsectsUseCase

    @Before
    fun setUp() {
        getAllInsectsUseCase = GetAllInsectsUseCaseImpl(
            insectLocalDatasource = mockInsectLocalDatasource
        )
    }
}