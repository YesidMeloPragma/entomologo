package com.pragma.entomologo.unittest.logic.datasources.counterRecordInsectDatasource.mapper.cointerRecordInsectDetailMapper

import com.pragma.entomologo.logic.datasources.counterRecordInsectDatasource.mapper.toCounterRecordInsectModel
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ToCounterRecordInsectModelTest : BaseCounterRecordInsectDetailMapperTest() {

    @Test
    fun successToCounterRecordInsectModelTest() = runTest {

        //Given
        val ids = 1.toLong()
        val comment = "comment"
        val counter = 3
        val nameSpecie = "nameSpecie"
        val urlPhoto = "UrlPhoto"
        val latlng = 1.0
        every { mockCounterRecordInsectDetailView.id } answers { ids }
        every { mockCounterRecordInsectDetailView.comment } answers { comment }
        every { mockCounterRecordInsectDetailView.count } answers { counter }
        every { mockCounterRecordInsectDetailView.insectId } answers { ids }
        every { mockCounterRecordInsectDetailView.specieName } answers { nameSpecie }
        every { mockCounterRecordInsectDetailView.urlPhoto } answers { urlPhoto }
        every { mockCounterRecordInsectDetailView.geoLocationId } answers { ids }
        every { mockCounterRecordInsectDetailView.lat } answers { latlng }
        every { mockCounterRecordInsectDetailView.lng } answers { latlng }

        //when
        val response = mockCounterRecordInsectDetailView.toCounterRecordInsectModel()

        Assert.assertEquals(response.id, ids )
        Assert.assertEquals(response.comment, comment)
        Assert.assertEquals(response.count, counter)
        Assert.assertEquals(response.insect.id, ids)
        Assert.assertEquals(response.insect.specieName, nameSpecie)
        Assert.assertEquals(response.insect.urlPhoto, urlPhoto)
        Assert.assertEquals(response.geoLocation.id, ids)
        Assert.assertEquals(response.geoLocation.lat, latlng, 0.0)
        Assert.assertEquals(response.geoLocation.lng, latlng, 0.0)

        //then
        verify(exactly = 1) { mockCounterRecordInsectDetailView.id }
        verify(exactly = 1) { mockCounterRecordInsectDetailView.comment }
        verify(exactly = 1) { mockCounterRecordInsectDetailView.count }
        verify(exactly = 1) { mockCounterRecordInsectDetailView.insectId }
        verify(exactly = 1) { mockCounterRecordInsectDetailView.specieName }
        verify(exactly = 1) { mockCounterRecordInsectDetailView.urlPhoto }
        verify(exactly = 1) { mockCounterRecordInsectDetailView.geoLocationId }
        verify(exactly = 1) { mockCounterRecordInsectDetailView.lat }
        verify(exactly = 1) { mockCounterRecordInsectDetailView.lng }

    }
}