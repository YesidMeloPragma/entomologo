package com.pragma.entomologo.ui.views.customs.buttons

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.pragma.entomologo.ui.utils.TestTags
import org.junit.Rule
import org.junit.Test

class ButtonRemoveTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testClickButtonRemove() {
        composeTestRule.setContent { ButtonRemove(modifier = Modifier, onClick = {}) }
        composeTestRule.onNodeWithTag(TestTags.IMAGE_BUTTON_REMOVE.id).performClick()
        composeTestRule.onNodeWithTag(TestTags.IMAGE_BUTTON_REMOVE.id).assertHasClickAction()
    }
}