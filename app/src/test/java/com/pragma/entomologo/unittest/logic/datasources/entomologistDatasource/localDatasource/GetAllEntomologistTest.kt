package com.pragma.entomologo.unittest.logic.datasources.entomologistDatasource.localDatasource

import com.pragma.entomologo.sources.database.entities.EntomologistEntity
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetAllEntomologistTest : BaseEntomologistLocalDatasourceTest() {

    @Test
    fun successGetAllEntomologistOneElement() = runTest {

        //Given
        val listEntities= listOf(mockEntomologistEntity)
        every { mockEntomologistDao.getAll()} returns flow {
            emit(listEntities)
        }

        //when
        entomologistLocalDatasource
            .getAllEntomologist()
            .collect {
                Assert.assertEquals(listEntities.size, it.size)
            }

        //then
        verify { mockEntomologistDao.getAll() }

    }

    @Test
    fun successGetAllEntomologistEmpty() = runTest {

        //Given
        val listEntities = emptyList<EntomologistEntity>()
        every { mockEntomologistDao.getAll()} returns flow {
            emit(listEntities)
        }

        //when
        entomologistLocalDatasource
            .getAllEntomologist()
            .collect {
                assert(it.isEmpty())
            }

        //then
        verify { mockEntomologistDao.getAll() }

    }
}