package com.pragma.entomologo.unittest.logic.usesCase.addInsectUseCase

import com.pragma.entomologo.logic.excepciones.LogicException
import com.pragma.entomologo.logic.excepciones.TypeExceptions
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InvokeTest : BaseAddInsectUseCaseTest() {

    @Test
    fun withoutSpecieNameInModel() = runTest {
        try {
            //Given
            val longArrayTest = longArrayOf(1,2,3,4,5)
            val image = ""

            coEvery { mockInsectModel.specieName } returns ""

            //When
            addInsectUseCase
                .invoke(
                    insectModel = mockInsectModel,
                    imageBase64 = image
                )
                .collect {
                    Assert.assertEquals(longArrayTest.first(), it)
                }

        } catch (e: LogicException) {
            //Then
            Assert.assertEquals(TypeExceptions.WITHOUT_NAMESPECIE, e.typeException)
        }
    }

    @Test
    fun withoutImage() = runTest {
        try {
            //Given
            val longArrayTest = longArrayOf(1,2,3,4,5)
            val image = ""

            coEvery { mockInsectModel.specieName } returns "nombre"
            coEvery { mockInsectModel.urlPhoto } returns ""

            //When
            addInsectUseCase
                .invoke(
                    insectModel = mockInsectModel,
                    imageBase64 = image
                )
                .collect {
                    Assert.assertEquals(longArrayTest.first(), it)
                }

        } catch (e: LogicException) {
            //Then
            Assert.assertEquals(TypeExceptions.WITHOUT_IMAGE_SPECIE, e.typeException)
        }
    }

    @Test
    fun withoutMoreInformation() = runTest {
        try {
            //Given
            val longArrayTest = longArrayOf(1,2,3,4,5)
            val image = ""

            coEvery { mockInsectModel.specieName } returns "nombre"
            coEvery { mockInsectModel.urlPhoto } returns "url"
            coEvery { mockInsectModel.moreInformation } returns ""

            //When
            addInsectUseCase
                .invoke(
                    insectModel = mockInsectModel,
                    imageBase64 = image
                )
                .collect {
                    Assert.assertEquals(longArrayTest.first(), it)
                }

        } catch (e: LogicException) {
            //Then
            Assert.assertEquals(TypeExceptions.WITHOUT_URL_DESCRIPTION, e.typeException)
        }
    }

    @Test
    fun successSaveInsectExistsPhotoAndInsectInDB() = runTest{
        //Given

        val image = "image"
        val ids = longArrayOf(1)
        val nameSpecie = "SpecieTest"
        val urlPhoto = "UrlPhoto"
        val moreInformation = "moreInformation"
        val insectId = 1.toLong()

        coEvery { mockInsectModel.specieName } answers { nameSpecie }
        coEvery { mockInsectModel.urlPhoto } answers { urlPhoto }
        coEvery { mockInsectModel.moreInformation } answers { moreInformation }
        coEvery { mockInsectImageLocalDatasource.existsImage(path = any()) } returns true
        coEvery { mockInsectLocalDatasource.existsInsect(nameSpecie = any()) } returns true
        coEvery { mockInsectLocalDatasource.getInsectIdByName(nameSpecie = any()) } returns insectId

        //When
        addInsectUseCase
            .invoke(
                insectModel = mockInsectModel,
                imageBase64 = image
            )
            .collect {
                Assert.assertEquals(ids.first(), it)
            }

        //Then
        coVerify(exactly = 1) { mockInsectImageLocalDatasource.existsImage(path = any()) }
        coVerify(exactly = 0) { mockInsectImageLocalDatasource.saveImageInsect(
            imageBase64 = any(),
            path = any(),
            fileName = any()
        )}
        coVerify(exactly = 1) { mockInsectLocalDatasource.existsInsect(nameSpecie = any())  }
        coVerify(exactly = 0) { mockInsectLocalDatasource.insertInsect(insectModel = any())  }
        coVerify(exactly = 1) { mockInsectLocalDatasource.getInsectIdByName(nameSpecie = any()) }
    }

    @Test
    fun successSaveInsectNotExistsPhotoAndExistsInsectInDB() = runTest{

        //Given
        val image = "image"
        val ids = longArrayOf(1)
        val nameSpecie = "SpecieTest"
        val urlPhoto = "UrlPhoto"
        val moreInformation = "moreInformation"
        val insectId = 1.toLong()

        coEvery { mockInsectModel.specieName } answers { nameSpecie }
        coEvery { mockInsectModel.urlPhoto } answers { urlPhoto }
        coEvery { mockInsectModel.moreInformation } answers { moreInformation }
        coEvery { mockInsectImageLocalDatasource.existsImage(path = any()) } returns false
        coEvery { mockInsectImageLocalDatasource.saveImageInsect(imageBase64 = any(), path= any(), fileName = any()) } returns null
        coEvery { mockInsectLocalDatasource.existsInsect(nameSpecie = any()) } returns true
        coEvery { mockInsectLocalDatasource.getInsectIdByName(nameSpecie = any()) } returns insectId

        //When
        addInsectUseCase
            .invoke(
                insectModel = mockInsectModel,
                imageBase64 = image
            )
            .collect {
                Assert.assertEquals(ids.first(), it)
            }

        //Then
        coVerify(exactly = 1) { mockInsectImageLocalDatasource.existsImage(path = any()) }
        coVerify(exactly = 1) { mockInsectImageLocalDatasource.saveImageInsect(
            imageBase64 = any(),
            path = any(),
            fileName = any()
        )}
        coVerify(exactly = 1) { mockInsectLocalDatasource.existsInsect(nameSpecie = any())  }
        coVerify(exactly = 0) { mockInsectLocalDatasource.insertInsect(insectModel = any())  }
        coVerify(exactly = 1) { mockInsectLocalDatasource.getInsectIdByName(nameSpecie = any()) }
    }

    @Test
    fun successSaveInsectExistsPhotoAndNotExistsInsectInDB() = runTest{

        //Given
        val image = "image"
        val ids = longArrayOf(1)
        val nameSpecie = "SpecieTest"
        val urlPhoto = "UrlPhoto"
        val moreInformation = "moreInformation"

        coEvery { mockInsectModel.id } answers { 1 }
        coEvery { mockInsectModel.specieName } answers { nameSpecie }
        coEvery { mockInsectModel.urlPhoto } answers { urlPhoto }
        coEvery { mockInsectModel.moreInformation } answers { moreInformation }
        coEvery { mockInsectImageLocalDatasource.existsImage(path = any()) } returns true
        coEvery { mockInsectImageLocalDatasource.saveImageInsect(imageBase64 = any(), path= any(), fileName = any()) } returns null
        coEvery { mockInsectLocalDatasource.existsInsect(nameSpecie = any()) } returns false
        coEvery { mockInsectLocalDatasource.insertInsect(insectModel = any()) } returns flow {
            emit(ids)
        }

        //When
        addInsectUseCase
            .invoke(
                insectModel = mockInsectModel,
                imageBase64 = image
            )
            .collect {
                Assert.assertEquals(ids.first(), it)
            }

        //Then
        coVerify(exactly = 1) { mockInsectImageLocalDatasource.existsImage(path = any()) }
        coVerify(exactly = 0) { mockInsectImageLocalDatasource.saveImageInsect(
            imageBase64 = any(),
            path = any(),
            fileName = any()
        )}
        coVerify(exactly = 1) { mockInsectLocalDatasource.existsInsect(nameSpecie = any())  }
        coVerify(exactly = 1) { mockInsectLocalDatasource.insertInsect(insectModel = any())  }
    }

    @Test
    fun successSaveInsectNotExistsPhotoAndNotExistsInsectInDB() = runTest{

        //Given
        val image = "image"
        val ids = longArrayOf(1)
        val nameSpecie = "SpecieTest"
        val urlPhoto = "UrlPhoto"
        val moreInformation = "moreInformation"

        coEvery { mockInsectModel.id } answers { 1 }
        coEvery { mockInsectModel.specieName } answers { nameSpecie }
        coEvery { mockInsectModel.urlPhoto } answers { urlPhoto }
        coEvery { mockInsectModel.moreInformation } answers { moreInformation }
        coEvery { mockInsectImageLocalDatasource.existsImage(path = any()) } returns false
        coEvery { mockInsectImageLocalDatasource.saveImageInsect(imageBase64 = any(), path= any(), fileName = any()) } returns null
        coEvery { mockInsectLocalDatasource.existsInsect(nameSpecie = any()) } returns false
        coEvery { mockInsectLocalDatasource.insertInsect(insectModel = any()) } returns flow {
            emit(ids)
        }

        //When
        addInsectUseCase
            .invoke(
                insectModel = mockInsectModel,
                imageBase64 = image
            )
            .collect {
                Assert.assertEquals(ids.first(), it)
            }

        //Then
        coVerify(exactly = 1) { mockInsectImageLocalDatasource.existsImage(path = any()) }
        coVerify(exactly = 1) { mockInsectImageLocalDatasource.saveImageInsect(
            imageBase64 = any(),
            path = any(),
            fileName = any()
        )}
        coVerify(exactly = 1) { mockInsectLocalDatasource.existsInsect(nameSpecie = any())  }
        coVerify(exactly = 1) { mockInsectLocalDatasource.insertInsect(insectModel = any())  }
    }

}