package com.pragma.entomologo.ui.views.images

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.pragma.entomologo.R

@Preview(showBackground = true)
@Composable
fun ImagePreviews() {
    Logo()
}

@Composable
fun Logo() {
    Image(painter = painterResource(id = R.mipmap.logo_entomology_1), contentDescription = "")
}