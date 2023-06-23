package com.pragma.entomologo.unittest.logic.datasources.insectDatasource.imageDatasource

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.entomologo.logic.datasources.insectDatasource.imageDatasource.InsectImageLocalDatasource
import com.pragma.entomologo.logic.datasources.insectDatasource.imageDatasource.InsectImageLocalDatasourceImpl
import com.pragma.entomologo.sources.appImageGallery.ImageAppGallery
import com.pragma.entomologo.tools.MainCoroutineRule
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseInsectImageLocalDatasourceTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var mockImageAppGallery: ImageAppGallery

    protected lateinit var insectImageLocalDatasource: InsectImageLocalDatasource

    @Before
    fun setUp() {
        insectImageLocalDatasource = InsectImageLocalDatasourceImpl(
            imageAppGallery = mockImageAppGallery
        )
    }
}