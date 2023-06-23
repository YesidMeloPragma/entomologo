package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DeleteTest : BaseEntomologistLocalDatasourceTest() {

    @Test
    fun successDelete() = runTest {

        //Given
        coEvery { mockEntomologistDao.deleteElement(element = anyVararg()) } answers {}

        //when
        val result = entomologistLocalDatasource.delete(entomologistModel = mockEntomologistModel)
        assert(result)

        //then
        coVerify { mockEntomologistDao.deleteElement(element = anyVararg()) }

    }
}