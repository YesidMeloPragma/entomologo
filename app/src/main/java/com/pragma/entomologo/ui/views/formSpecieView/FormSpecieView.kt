package com.pragma.entomologo.ui.views.formSpecieView

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.pragma.entomologo.R
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.utils.extentions.getBitmap
import com.pragma.entomologo.ui.views.customs.buttons.CustomRoundedButtonsWithElevation
import com.pragma.entomologo.ui.views.customs.dialogs.progressDialog.ProgressDialog
import com.pragma.entomologo.ui.views.customs.images.CustomCircularImage
import com.pragma.entomologo.ui.views.customs.texts.CustomAutocompleteText
import com.pragma.entomologo.ui.views.customs.texts.CustomTextField
import com.pragma.entomologo.ui.views.formSpecieView.viewModel.FormSpecieViewModel

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
            FormSpecieView(modifier = Modifier.constrainAs(constraintsId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            },
                viewModel = object : FormSpecieViewModel() {
                    override fun loadView() { Log.i("Informa", "elemento")}
                    override fun setNameInsect(nameInsect: String) { Log.i("Informa", "elemento") }
                    override fun setMoreInformation(moreInformation: String) { Log.i("Informa", "elemento") }
                    override fun setSelectInsect(insectSelected: InsectModel) { Log.i("Informa", "elemento") }
                    override fun updateUIState(
                        loadingState: LoadingState,
                        imageProfile: String?,
                        nameInsect: String,
                        moreInformation: String,
                        listInsect: List<InsectModel>,
                        insectSelected: InsectModel?
                    ) {
                        Log.i("Informa", "elemento")
                    }
                },
                navigateToImageProfile = {}
            )
        }
    }
}

@Composable
fun FormSpecieView(
    modifier: Modifier = Modifier,
    viewModel: FormSpecieViewModel = hiltViewModel(),
    navigateToImageProfile: () -> Unit
) {
    //region stateObserver
    val currentState by viewModel.uiState.collectAsState(initial = FormSpecieViewModel.FormSpecieUIState())
    logicApplyInView(
        currentState = currentState,
        viewModel = viewModel
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
            currentState = currentState
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
    currentState: FormSpecieViewModel.FormSpecieUIState
) {
    ConstraintLayout(
        modifier = modifier
            .aspectRatio(1.3f)
    ) {
        if(currentState.loadingState == FormSpecieViewModel.LoadingState.LOADING) {
            ProgressDialog()
        }

        val (imageProfileId, imageInsectId) = createRefs()

        //region imageProfile
        val guidelineEndImageProfile = createGuidelineFromEnd(0.78f)
        val guidelineStartImageProfile = createGuidelineFromStart(0.06f)
        val guidelineTopImageProfile = createGuidelineFromTop(0.2f)
        CustomCircularImage(
            modifier = Modifier
                .constrainAs(imageProfileId) {
                    end.linkTo(guidelineEndImageProfile)
                    start.linkTo(guidelineStartImageProfile)
                    top.linkTo(guidelineTopImageProfile)
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(1f),
            placeHolder = R.mipmap.avatar,
            bitmap = currentState.imageProfile?.getBitmap(),
            contentScale = ContentScale.FillHeight
        )
        //endregion

        //region image insect
        val guidelineEndImageInsect = createGuidelineFromEnd(0.35f)
        val guidelineStartImageInsect = createGuidelineFromStart(0.35f)
        CustomCircularImage(
            colorBorder = MaterialTheme.colorScheme.primaryContainer,
            placeHolder = R.drawable.scorpion,
            contentScale = ContentScale.FillHeight,
            widthBorder = 5.dp,
            modifier = Modifier
                .constrainAs(imageInsectId) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(guidelineEndImageInsect)
                    start.linkTo(guidelineStartImageInsect)
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(1f),
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
            viewModel = viewModel
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
            }
        )
        //endregion
    }
}

@Composable
fun FormInsect(
    modifier: Modifier,
    currentState: FormSpecieViewModel.FormSpecieUIState,
    viewModel: FormSpecieViewModel
) {

    //region variables
    val moreInformation = rememberSaveable { mutableStateOf("") }

    //endregion

    ConstraintLayout(modifier = modifier) {
        val (nameSpecieId, moreInfomationId ) = createRefs()

        //region nameSpecie
        CustomAutocompleteText(
            modifier = Modifier.constrainAs(nameSpecieId){},
            list = currentState.listInsect,
            itemSelected = viewModel::setSelectInsect,
            onValueChanged = viewModel::setNameInsect,
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
                viewModel.setMoreInformation(moreInformation = it)
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
    viewModel: FormSpecieViewModel
) {
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

            }
        )
    }
}

//endregion

private fun logicApplyInView(
    currentState: FormSpecieViewModel.FormSpecieUIState,
    viewModel: FormSpecieViewModel
) {
    when (currentState.loadingState) {
        FormSpecieViewModel.LoadingState.START -> viewModel.loadView()
        FormSpecieViewModel.LoadingState.LOADING -> {}
        FormSpecieViewModel.LoadingState.LOADED -> {}
    }
}