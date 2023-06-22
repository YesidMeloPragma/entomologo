package com.pragma.entomologo.ui.views.customs.switchs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.pragma.entomologo.ui.utils.TestTags
import org.junit.Rule
import org.junit.Test

class CustomSwitchRoundedTest {

    @get:Rule
    val composableTestRule = createComposeRule()

    @Test
    fun checkPreview() {
        val title = "title"
        val message = "message"
        composableTestRule.setContent { CustomSwitchRounded(modifier = Modifier.fillMaxSize(), title = title, legend = message, onCheckedChanged = {}) }
        composableTestRule.onNodeWithTag(TestTags.CUSTOM_SWITCH_SWITCH.id).assertIsDisplayed()
        composableTestRule.onNodeWithTag(TestTags.CUSTOM_SWITCH_TITLE.id).assertIsDisplayed()
        composableTestRule.onNodeWithTag(TestTags.CUSTOM_SWITCH_MESSAGE.id).assertIsDisplayed()
    }

}