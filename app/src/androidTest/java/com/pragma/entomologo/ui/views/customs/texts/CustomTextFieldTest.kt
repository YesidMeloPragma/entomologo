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

class CustomTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkVisibility() {
        var changed = ""
        val label = "Label"
        composeTestRule.setContent {
            CustomTextField(
                modifier = Modifier.testTag(TestTags.CUSTOM_TEXT_FIELD.id).fillMaxSize(),
                onValueChange = { changed = it},
                value = changed,
                label = label
            )
        }

        composeTestRule.onNodeWithTag(TestTags.CUSTOM_TEXT_FIELD.id).assertIsDisplayed()
    }
}