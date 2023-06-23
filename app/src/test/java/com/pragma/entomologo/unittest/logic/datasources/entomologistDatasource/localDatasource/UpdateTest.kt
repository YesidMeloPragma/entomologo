package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UpdateTest : BaseEntomologistLocalDatasourceTest() {

    @Test
    fun successInsertTest() = runTest {

        //Given
        coEvery { mockEntomologistDao.updateElement(element = any())} answers {}

        //when
        val result = entomologistLocalDatasource.update(entomologistModel = mockEntomologistModel)
        assert(result)

        //then
        coVerify { mockEntomologistDao.updateElement(element = any()) }

    }
}