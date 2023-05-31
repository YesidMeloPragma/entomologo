package com.pragma.entomologo.ui.views.customs.buttons

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.pragma.entomologo.ui.utils.TestTags
import org.junit.Rule
import org.junit.Test

class CustomRoundedButtomTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickOnButton() {
        val text = "test"
        composeTestRule.setContent { CustomRoundedButtom(modifier = Modifier.testTag(TestTags.CUSTOM_BUTTON.id), text = text, onClick = {}) }
        composeTestRule.onNodeWithTag(TestTags.CUSTOM_BUTTON.id).performClick()
        composeTestRule.onNodeWithTag(TestTags.CUSTOM_BUTTON.id).assertHasClickAction()
    }
}