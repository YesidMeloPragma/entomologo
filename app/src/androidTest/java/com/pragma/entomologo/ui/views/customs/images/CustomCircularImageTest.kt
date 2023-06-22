package com.pragma.entomologo.ui.views.customs.images

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.pragma.entomologo.ui.utils.TestTags
import org.junit.Rule
import org.junit.Test

class CustomCircularImageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkImageToShow() {
        composeTestRule.setContent { CustomCircularImage(modifier = Modifier.testTag(TestTags.IMAGE_BUTTON_REMOVE.id).fillMaxWidth(), route = android.R.drawable.ic_delete)}
        composeTestRule.onNodeWithTag(TestTags.IMAGE_BUTTON_REMOVE.id).assertIsDisplayed()
    }
}