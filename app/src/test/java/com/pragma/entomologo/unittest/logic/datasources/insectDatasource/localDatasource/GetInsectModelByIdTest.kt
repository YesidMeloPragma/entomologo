package com.pragma.entomologo.unittest.logic.datasources.insectDatasource.localDatasource

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetInsectModelByIdTest : BaseInsectLocalDatasourceTest() {

    @Test
    fun successGetInsectModelByIdTest() = runTest {
        //Given
        val id = 1.toLong()
        val specieName = "specieName"
        val urlPhoto = "urlphoto"
        val moreInformacion = "moreInformation"

        coEvery { mockInsectDao.getInsectEntityById(insectId = any()) } returns mockInsectEntity
        every { mockInsectEntity.id } answers { id }
        every { mockInsectEntity.specieName } answers { specieName }
        every { mockInsectEntity.urlPhoto } answers { urlPhoto }
        every { mockInsectEntity.moreInformation } answers { moreInformacion }

        //when
        val model = insectLocalDatasource.getInsectModelById(insectId = 1)
        Assert.assertEquals(id, model.id)
        Assert.assertEquals(specieName, model.specieName)
        Assert.assertEquals(urlPhoto, model.urlPhoto)
        Assert.assertEquals(moreInformacion, model.moreInformation)

        //then
        coVerify(exactly = 1) { mockInsectDao.getInsectEntityById(insectId = any()) }
        verify(exactly = 1) { mockInsectEntity.id }
        verify(exactly = 1) { mockInsectEntity.specieName }
        verify(exactly = 1) { mockInsectEntity.urlPhoto }
        verify(exactly = 1) { mockInsectEntity.moreInformation }
    }
}