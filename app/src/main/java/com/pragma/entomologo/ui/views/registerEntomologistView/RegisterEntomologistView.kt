package com.pragma.entomologo.ui.views.registerEntomologistView


import android.content.res.Configuration
import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.R
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.views.customs.buttons.CustomButtomOutline
import com.pragma.entomologo.ui.views.customs.buttons.CustomRoundedButtonsWithElevation
import com.pragma.entomologo.ui.views.customs.dialogs.progressDialog.ProgressDialog
import com.pragma.entomologo.ui.views.customs.images.CustomCircularImage
import com.pragma.entomologo.ui.views.customs.switchs.CustomSwitchRounded
import com.pragma.entomologo.ui.views.customs.texts.CustomTextField
import com.pragma.entomologo.ui.views.registerEntomologistView.viewModel.RegisterEntomologyViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private var navigate = true

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun RegisterEntomologistViewPreview() {
    EntomologoTheme {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val constraintsId = createRef()
            val stateUI = MutableStateFlow(value = RegisterEntomologyViewModel.StateUI())

            RegisterEntomologistView(modifier = Modifier.constrainAs(constraintsId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
                viewModel = object: RegisterEntomologyViewModel() {
                    override fun stateUI(): StateFlow<StateUI> = stateUI
                    override fun checkPermissionsGPS() {updateState()}

                    override fun saveEntomologist() { updateState() }

                    override fun setCurrentImageProfile(image: Bitmap?) { updateState() }

                    override fun setCurrentNameEntomologist(name: String) {
                        updateState(name = name)
                    }

                    override fun setCurrentStatusSwitch(statusSwitch: Boolean) {
                        updateState(statusSwitch = statusSwitch)
                    }

                    private fun updateState(
                        bitmap: Bitmap? = stateUI.value.bitmap,
                        enableInteraction: Boolean = stateUI.value.enableInteraction,
                        loading: Boolean = stateUI.value.loading,
                        name: String = stateUI.value.name,
                        navigateToListRecords: Boolean = stateUI.value.navigateToListRecords,
                        statusSwitch: Boolean = stateUI.value.statusSwitch,
                    ) {
                        viewModelScope.launch {
                            stateUI.emit(StateUI(
                                bitmap = bitmap,
                                enableInteraction = enableInteraction,
                                loading = loading,
                                name = name,
                                navigateToListRecords = navigateToListRecords,
                                statusSwitch = statusSwitch,
                            ))
                        }
                    }
                },
                navigateToCatureImage = {},
                navigateToListRecords = {},
            )
        }
    }
}

@Composable
fun RegisterEntomologistView(
    modifier: Modifier,
    viewModel: RegisterEntomologyViewModel,
    navigateToCatureImage : ()->Unit,
    navigateToListRecords: ()->Unit,
) {
    //region variables
    val stateUI by viewModel.stateUI().collectAsState(initial = RegisterEntomologyViewModel.StateUI())
    val name = rememberSaveable{ mutableStateOf("") }
    logicUI(
        stateUI = stateUI,
        navigateToListRecords = navigateToListRecords,
        viewModel = viewModel
    )
    //endregion

    ConstraintLayout(modifier = modifier.background(color = MaterialTheme.colorScheme.secondaryContainer)) {
        val (imageProfileId, nameId, switchId, firstButtonId, secondButtonId) = createRefs()

        //region image profile
        val guideLineEndImage = createGuidelineFromEnd(fraction = 0.35f)
        val guideLineStartImage = createGuidelineFromStart(fraction = 0.35f)
        val guideLineTopImage = createGuidelineFromTop(fraction = 0.23f)

        CustomCircularImage(
            modifier = Modifier
                .constrainAs(imageProfileId) {
                    end.linkTo(guideLineEndImage)
                    start.linkTo(guideLineStartImage)
                    top.linkTo(guideLineTopImage)
                    width = Dimension.fillToConstraints
                }
                .clickable {
                    if (!stateUI.enableInteraction) return@clickable
                    navigateToCatureImage.invoke()
                },
            bitmap = stateUI.bitmap,
            placeHolder = R.mipmap.avatar,
            contentScale = ContentScale.FillHeight
        )
        //endregion

        //region name

        val guideLineEndName = createGuidelineFromEnd(fraction = 0.11f)
        val guideLineStartName = createGuidelineFromStart(fraction = 0.11f)

        CustomTextField(
            modifier = Modifier.constrainAs(nameId) {
                end.linkTo(guideLineEndName)
                start.linkTo(guideLineStartName)
                top.linkTo(imageProfileId.bottom, margin = 31.dp)
                width = Dimension.fillToConstraints
            },
            value = name.value,
            onValueChange = {
                name.value = it
                viewModel.setCurrentNameEntomologist(name = it)
            },
            label = "Nombre"
        )
        //endregion

        //region customs switch
        val guideLineEndSwitch = createGuidelineFromEnd(fraction = 0.06f)
        val guideLineStartSwitch = createGuidelineFromStart(fraction = 0.06f)
        val guideLineTopSwitch = createGuidelineFromTop(fraction = 0.6f)

        CustomSwitchRounded(
            modifier = Modifier.constrainAs(switchId){
                start.linkTo(guideLineStartSwitch)
                end.linkTo(guideLineEndSwitch)
                top.linkTo(guideLineTopSwitch)
                width = Dimension.fillToConstraints
            },
            title = "Compartenos tu ubicacion",
            legend = "Deja que Entomology conozca tu ubicación para gestionar tus registros de una mejor manera",
            onCheckedChanged = viewModel::setCurrentStatusSwitch,
            checked = stateUI.statusSwitch
        )
        //endregion

        //region buttons
        if(stateUI.enableInteraction) {
            val guideLineBottomButtons = createGuidelineFromBottom(fraction = 0.14f)

            CustomButtomOutline(
                modifier = Modifier.constrainAs(firstButtonId){
                    bottom.linkTo(guideLineBottomButtons)
                    end.linkTo(secondButtonId.start)
                    start.linkTo(guideLineStartSwitch)
                },
                text = "Omitir" ,
                onClick = {
                    navigateToListRecords.invoke()
                }
            )

            CustomRoundedButtonsWithElevation(
                modifier = Modifier.constrainAs(secondButtonId){
                    bottom.linkTo(guideLineBottomButtons)
                    end.linkTo(guideLineEndSwitch)
                    start.linkTo(firstButtonId.end)
                },
                text = "Guardar",
                onClick = {
                    viewModel.saveEntomologist()
                }
            )
        }
        //endregion

        //region progress dialog
        if(stateUI.loading) {
            ProgressDialog()
        }
        //endregion
    }
}

private fun logicUI(
    stateUI: RegisterEntomologyViewModel.StateUI,
    navigateToListRecords: ()->Unit,
    viewModel: RegisterEntomologyViewModel
) {
    viewModel.checkPermissionsGPS()
    if(!stateUI.navigateToListRecords) return
    if (!navigate) return
    navigate = false
    navigateToListRecords.invoke()
}