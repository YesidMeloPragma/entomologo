package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.imageDatasource

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource.EntomologistImageDatasource
import com.pragma.entomologo.logic.datasources.entomologistDatasource.imageDatasource.EntomologistImageDatasourceImpl
import com.pragma.entomologo.sources.appImageGallery.ImageAppGallery
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseEntomologistImageDatasourceTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var mockImageAppGallery: ImageAppGallery

    protected lateinit var entomologistImageDatasource: EntomologistImageDatasource

    @Before
    fun setUp() {
        entomologistImageDatasource = EntomologistImageDatasourceImpl(
            imageAppGallery = mockImageAppGallery
        )
    }
}