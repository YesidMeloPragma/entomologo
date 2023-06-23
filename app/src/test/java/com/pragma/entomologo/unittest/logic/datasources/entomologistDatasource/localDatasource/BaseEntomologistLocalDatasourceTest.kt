package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.localDatasource

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.entomologistDatasource.localDatasource.EntomologistLocalDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.localDatasource.EntomologistLocalDatasourceImpl
import com.pragma.entomologo.logic.models.EntomologistModel
import com.pragma.entomologo.sources.database.dao.EntomologistDao
import com.pragma.entomologo.sources.database.entities.EntomologistEntity
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseEntomologistLocalDatasourceTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var mockEntomologistDao: EntomologistDao

    @RelaxedMockK
    lateinit var mockEntomologistModel: EntomologistModel

    @RelaxedMockK
    lateinit var mockEntomologistEntity: EntomologistEntity

    protected lateinit var entomologistLocalDatasource: EntomologistLocalDatasource

    @Before
    fun setUp() {
        entomologistLocalDatasource = EntomologistLocalDatasourceImpl(
            entomologistDao = mockEntomologistDao
        )
    }
}