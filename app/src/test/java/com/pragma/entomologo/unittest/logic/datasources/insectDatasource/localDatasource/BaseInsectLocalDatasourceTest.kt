package com.pragma.entomologo.unittest.logic.datasources.insectDatasource.localDatasource

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource.InsectLocalDatasource
import com.pragma.entomologo.logic.datasources.insectDatasource.localDatasource.InsectLocalDatasourceImpl
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.sources.database.dao.InsectDao
import com.pragma.entomologo.sources.database.entities.InsectEntity
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseInsectLocalDatasourceTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var mockInsectDao: InsectDao

    @RelaxedMockK
    lateinit var mockInsectModel: InsectModel

    @RelaxedMockK
    lateinit var mockInsectEntity: InsectEntity

    protected lateinit var insectLocalDatasource: InsectLocalDatasource

    @Before
    fun setUp() {
        insectLocalDatasource = InsectLocalDatasourceImpl(
            insectDao = mockInsectDao
        )
    }
}