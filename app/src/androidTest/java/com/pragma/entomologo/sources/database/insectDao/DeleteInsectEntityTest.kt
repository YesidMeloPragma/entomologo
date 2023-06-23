package com.pragma.entomologo.sources.database.insectDao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pragma.entomologo.sources.database.entities.InsectEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class DeleteInsectEntityTest : BaseInsectDaoTest() {

    @Test
    fun DeleteInsectEntityIntoDB() = runTest {

        //Given
        val entity = InsectEntity(
            specieName = "SpaceName",
            urlPhoto = "UrlPhoto",
            moreInformation = "MoreInformation"
        )
        //When
        insectDao.insertElement(entity)
        insectDao.deleteElement(entity.apply { id = 1 })

        val insectFlow = insectDao.getAllInsects()
        val list = mutableListOf<InsectEntity>()

        val job = launch {

            insectFlow.collect {
                list.addAll(it)
                //Then
                assert(it.isEmpty())
            }
        }

        //finish job
        advanceUntilIdle()
        job.cancel()

    }
}