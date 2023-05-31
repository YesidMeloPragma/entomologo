package com.pragma.entomologo.ui.views.customs.texts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.pragma.entomologo.ui.utils.TestTags
import org.junit.Rule
import org.junit.Test

class CustomTextTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkText() {
        val text = "text"
        composeTestRule.setContent { CustomText(modifier = Modifier.testTag(TestTags.CUSTOM_TEXT.id).fillMaxSize(), text = text) }
        composeTestRule.onNodeWithTag(TestTags.CUSTOM_TEXT.id).assertIsDisplayed()
    }
}