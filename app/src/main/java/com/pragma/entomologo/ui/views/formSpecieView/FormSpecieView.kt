package com.pragma.entomologo.ui.views.formSpecieView

import android.content.res.Configuration
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.pragma.entomologo.R
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.ui.dialogs.errorDialog.ErrorDialogView
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.views.app.imageProfile.ImageProfileView
import com.pragma.entomologo.ui.views.app.imageProfile.viewModel.ImageProfileViewModel
import com.pragma.entomologo.ui.views.app.imageProfile.viewModel.ImageProfileViewModelImpl
import com.pragma.entomologo.ui.views.app.loadImageInsectFromGallery.LoadImageInsectFromGalleryView
import com.pragma.entomologo.ui.views.app.loadImageInsectFromGallery.viewModel.LoadImageInsectFromGalleryViewModel
import com.pragma.entomologo.ui.views.app.loadImageInsectFromGallery.viewModel.LoadImageInsectFromGalleryViewModelImpl
import com.pragma.entomologo.ui.views.customs.buttons.CustomRoundedButtonsWithElevation
import com.pragma.entomologo.ui.views.customs.dialogs.progressDialog.ProgressDialog
import com.pragma.entomologo.ui.views.customs.texts.CustomAutocompleteText
import com.pragma.entomologo.ui.views.customs.texts.CustomTextField
import com.pragma.entomologo.ui.views.formSpecieView.viewModel.FormSpecieViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PHONE
)
@Composable
fun FormSpecieViewPreview() {
    EntomologoTheme {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            val constraintsId = createRef()
            val statusImageProfileUI = MutableStateFlow(value = ImageProfileViewModel.ImageProfileUIState())
            FormSpecieView(modifier = Modifier.constrainAs(constraintsId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            },
                viewModel = object : FormSpecieViewModel() {
                    override fun closeError() {Log.i("Informa", "elemento")}
                    override fun loadView() { Log.i("Informa", "elemento")}
                    override fun saveRecord(nameInsect: String, moreInformation: String) {Log.i("Informa", "elemento")}
                    override fun setSelectInsect(insectSelected: InsectModel) { Log.i("Informa", "elemento") }
                    override fun setImage(imageBase64: String) {Log.i("Informa", "elemento")}
                },
                navigateToImageProfile = {},
                imageProfileViewModel = object : ImageProfileViewModel() {
                    override fun loadImage() {
                        Log.i("Informa", "Informacion")
                    }

                    override fun setImageSelected(bitmap: Bitmap?) {
                        Log.i("Informa", "Informacion")
                    }

                    override fun getStateUI(): StateFlow<ImageProfileUIState> = statusImageProfileUI
                },
                loadImageInsectFromGalleryViewModel = object : LoadImageInsectFromGalleryViewModel(){
                    override fun loadUI(insectModel: InsectModel?) {
                        Log.i("Informacion", "Elemento")
                    }

                    override fun updatePhotoInsect(image: String?) {
                        Log.i("Informacion", "Elemento")
                    }

                },
                navigateToListRecordsInsect = {}
            )
        }
    }
}

@Composable
fun FormSpecieView(
    modifier: Modifier = Modifier,
    viewModel: FormSpecieViewModel = hiltViewModel(),
    imageProfileViewModel : ImageProfileViewModel = hiltViewModel<ImageProfileViewModelImpl>(),
    loadImageInsectFromGalleryViewModel: LoadImageInsectFromGalleryViewModel = hiltViewModel<LoadImageInsectFromGalleryViewModelImpl>(),
    navigateToImageProfile: () -> Unit,
    navigateToListRecordsInsect: (insect: InsectModel) -> Unit
) {
    //region stateObserver
    val currentState by viewModel.uiState.collectAsState(initial = FormSpecieViewModel.FormSpecieUIState())
    logicApplyInView(
        currentState = currentState,
        viewModel = viewModel,
        navigateToListRecordsInsect = navigateToListRecordsInsect
    )
    //endregion

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (headerId, bodyId) = createRefs()
        Header(
            modifier = Modifier
                .constrainAs(headerId){
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                },
            currentState = currentState,
            imageProfileViewModel = imageProfileViewModel,
            loadImageInsectFromGalleryViewModel= loadImageInsectFromGalleryViewModel,
            navigateToImageProfile = navigateToImageProfile,
            formViewModel = viewModel
        )

        Body(
            modifier = Modifier.constrainAs(bodyId){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(headerId.bottom)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            },
            currentState = currentState,
            viewModel = viewModel
        )
    }
}

//region ui
@Composable
fun Header(
    modifier: Modifier,
    currentState: FormSpecieViewModel.FormSpecieUIState,
    formViewModel: FormSpecieViewModel,
    imageProfileViewModel : ImageProfileViewModel,
    loadImageInsectFromGalleryViewModel: LoadImageInsectFromGalleryViewModel,
    navigateToImageProfile: () -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
            .aspectRatio(1.3f)
    ) {

        if(
            currentState.loadingState == FormSpecieViewModel.LoadingState.LOADING ||
            currentState.loadingState == FormSpecieViewModel.LoadingState.NAVIGATE_TO_COUNTER_RECORD
        ) {
            ProgressDialog()
        }

        if(currentState.logicException != null) {
            ErrorDialogView(
                onDismiss = { formViewModel.closeError() },
                exception = currentState.logicException
            )
        }

        val (imageProfileId, imageInsectId) = createRefs()

        //region imageProfile
        val guidelineEndImageProfile = createGuidelineFromEnd(0.78f)
        val guidelineStartImageProfile = createGuidelineFromStart(0.06f)
        val guidelineTopImageProfile = createGuidelineFromTop(0.2f)
        ImageProfileView(
            viewModel = imageProfileViewModel,
            action = navigateToImageProfile,
            modifier = Modifier
                .constrainAs(imageProfileId) {
                    end.linkTo(guidelineEndImageProfile)
                    start.linkTo(guidelineStartImageProfile)
                    top.linkTo(guidelineTopImageProfile)
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(1f)
        )
        //endregion

        //region image insect
        val guidelineEndImageInsect = createGuidelineFromEnd(0.35f)
        val guidelineStartImageInsect = createGuidelineFromStart(0.35f)
        LoadImageInsectFromGalleryView(
            insectModel = currentState.insectSelected,
            modifier = Modifier
                .constrainAs(imageInsectId) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(guidelineEndImageInsect)
                    start.linkTo(guidelineStartImageInsect)
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(1f),
            viewModel = loadImageInsectFromGalleryViewModel,
            imageSelected = {
                formViewModel.setImage(imageBase64 = it?:"")
            }
        )
        //endregion

    }
}

@Composable
fun Body(
    modifier: Modifier,
    currentState: FormSpecieViewModel.FormSpecieUIState,
    viewModel: FormSpecieViewModel
) {
    val moreInformation = rememberSaveable { mutableStateOf("") }
    val nameInsect = rememberSaveable { mutableStateOf("") }

    ConstraintLayout(modifier = modifier) {
        val (formInsectId,buttonsId) = createRefs()

        //region formInsect
        val marginEnd = createGuidelineFromEnd(0.1f)
        val marginStart = createGuidelineFromStart(0.1f)
        val margionTop = createGuidelineFromTop(0.09f)
        FormInsect(
            modifier = Modifier.constrainAs(formInsectId){
                end.linkTo(marginEnd)
                start.linkTo(marginStart)
                top.linkTo(margionTop)
                width = Dimension.fillToConstraints
            },
            currentState = currentState,
            viewModel = viewModel,
            moreInformation = moreInformation,
            nameInsect = nameInsect
        )
        //endregion

        //region button
        ButtonSelect(
            currentState = currentState,
            viewModel = viewModel,
            modifier = Modifier.constrainAs(buttonsId){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(formInsectId.bottom)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            },
            moreInformation = moreInformation,
            nameInsect = nameInsect
        )
        //endregion
    }
}

@Composable
fun FormInsect(
    modifier: Modifier,
    currentState: FormSpecieViewModel.FormSpecieUIState,
    viewModel: FormSpecieViewModel,
    moreInformation: MutableState<String>,
    nameInsect: MutableState<String>,
) {
    ConstraintLayout(modifier = modifier) {
        val (nameSpecieId, moreInfomationId ) = createRefs()

        //region nameSpecie
        CustomAutocompleteText(
            modifier = Modifier.constrainAs(nameSpecieId){},
            list = currentState.listInsect,
            itemSelected = {
                moreInformation.value = it.moreInformation
                viewModel.setSelectInsect(insectSelected = it)
            },
            onValueChanged = {
                nameInsect.value = it
            },
            label = stringResource(id = R.string.name_specie)
        )
        //endregion

        //region more information
        CustomTextField(
            modifier = Modifier.constrainAs(moreInfomationId){
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(nameSpecieId.bottom, margin = 10.dp)
                width = Dimension.fillToConstraints
            },
            value = moreInformation.value,
            onValueChange = {
                moreInformation.value = it
            },
            label = stringResource(id = R.string.more_information)
        )
        //endregion
    }
}

@Composable
fun ButtonSelect(
    modifier: Modifier,
    currentState: FormSpecieViewModel.FormSpecieUIState,
    viewModel: FormSpecieViewModel,
    moreInformation: MutableState<String>,
    nameInsect: MutableState<String>,
) {
    if(currentState.loadingState == FormSpecieViewModel.LoadingState.LOADING) return
    ConstraintLayout(modifier = modifier) {
        val buttonId = createRef()

        CustomRoundedButtonsWithElevation(
            modifier = Modifier.constrainAs(buttonId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            },
            text = stringResource(id = R.string.select),
            onClick = {
                viewModel.saveRecord(
                    nameInsect = nameInsect.value,
                    moreInformation = moreInformation.value
                )
            }
        )
    }
}

//endregion

private fun logicApplyInView(
    currentState: FormSpecieViewModel.FormSpecieUIState,
    viewModel: FormSpecieViewModel,
    navigateToListRecordsInsect: (insect: InsectModel) -> Unit
) {
    if(currentState.loadingState == FormSpecieViewModel.LoadingState.START){
        viewModel.loadView()
        return
    }

    if(currentState.loadingState ==  FormSpecieViewModel.LoadingState.NAVIGATE_TO_COUNTER_RECORD) {
        navigateToListRecordsInsect.invoke(currentState.insectSelected!!)
        return
    }
}