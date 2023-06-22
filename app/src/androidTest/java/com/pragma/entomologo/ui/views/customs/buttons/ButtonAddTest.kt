package com.pragma.entomologo.ui.views.customs.buttons

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.pragma.entomologo.ui.utils.TestTags
import org.junit.Rule
import org.junit.Test

class ButtonAddTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testClickButtonAdd() {
        composeTestRule.setContent { ButtonAdd(modifier = Modifier.fillMaxSize(), onClick = {}) }
        composeTestRule.onNodeWithTag(TestTags.IMAGE_BUTTON_ADD.id).performClick()
        composeTestRule.onNodeWithTag(TestTags.IMAGE_BUTTON_ADD.id).assertHasClickAction()
    }
}