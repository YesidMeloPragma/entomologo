package com.pragma.entomologo.ui.views.speciesRecordsView

import android.content.res.Configuration
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewModelScope
import com.pragma.entomologo.R
import com.pragma.entomologo.logic.models.CounterRecordInsectModel
import com.pragma.entomologo.logic.models.GeoLocationModel
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.ui.activities.ActivityState
import com.pragma.entomologo.ui.dialogs.errorDialog.ErrorDialogView
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.views.app.NewCounterView
import com.pragma.entomologo.ui.views.app.imageProfile.ImageProfileView
import com.pragma.entomologo.ui.views.app.imageProfile.viewModel.ImageProfileViewModel
import com.pragma.entomologo.ui.views.customs.buttons.CustomButtomOutline
import com.pragma.entomologo.ui.views.customs.buttons.CustomRoundedButtonsWithElevation
import com.pragma.entomologo.ui.views.customs.dialogs.progressDialog.ProgressDialog
import com.pragma.entomologo.ui.views.requestPermisions.EnablePermissionsNecessariesInConfigApp
import com.pragma.entomologo.ui.views.speciesRecordsView.viewModel.SpeciesRecordsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

@Composable
fun SpeciesRecordsView(
    modifier: Modifier,
    viewModel: SpeciesRecordsViewModel,
    imageProfileViewModel: ImageProfileViewModel,
    navigateToImageProfile: () -> Unit,
    navigateToRegisterNewInsect: () -> Unit,
    navigateToReports: () -> Unit,
    stateActivity : MutableState<ActivityState>,
) {

    //region variables
    val stateUI by viewModel.getStateUI().collectAsState(initial = SpeciesRecordsViewModel.StatusUISpeciesRecord())
    val showRequestPermission = remember { mutableStateOf(false) }
    logicView(
        statusUI = stateUI,
        viewModel = viewModel,
        stateActivity = stateActivity
    )
    //endregion
    ConstraintLayout(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        val (
            imageId,
            counterAndListId,
            buttonsId,
        ) = createRefs()

        //region profileImage
        val guidelineImageEnd = createGuidelineFromEnd(fraction = 0.78f)
        val guidelineImageStart = createGuidelineFromStart(fraction = 0.06f)
        val guidelineImageTop = createGuidelineFromTop(fraction = 0.07f)

        ImageProfileView(
            modifier = Modifier
                .constrainAs(imageId) {
                    end.linkTo(guidelineImageEnd)
                    start.linkTo(guidelineImageStart)
                    top.linkTo(guidelineImageTop)
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(1f),
            viewModel = imageProfileViewModel,
            action = navigateToImageProfile
        )
        //endregion

        //region list records
        val guidelineListRecordsBottom = createGuidelineFromTop(0.926f)
        HandlerCounterAndList(modifier = Modifier
            .constrainAs(counterAndListId){
                bottom.linkTo(guidelineListRecordsBottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(imageId.bottom, margin = 23.dp)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            },
            stateUI = stateUI,
            navigateToRegisterNewInsect = navigateToRegisterNewInsect,
        )
        //endregion

        //region buttons
        val guidelineButtonsEnd = createGuidelineFromEnd(0.131f)
        val guidelineButtonsStart = createGuidelineFromStart(0.131f)
        val guidelineButtonsTop = createGuidelineFromTop(0.926f)
        HandlerButtons(
            modifier = Modifier
                .constrainAs(buttonsId) {
                    end.linkTo(guidelineButtonsEnd)
                    start.linkTo(guidelineButtonsStart)
                    top.linkTo(guidelineButtonsTop)
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(7.6f),
            navigateToResume = navigateToReports,
            statusUI = stateUI,
            viewModel = viewModel
        )
        //endregion

        HandlerDialogs(
            stateUI = stateUI,
            viewModel = viewModel,
            showRequestPermission = showRequestPermission
        )

    }
}
//region details
//region handlerDialogs
@Composable
fun HandlerDialogs(
    stateUI: SpeciesRecordsViewModel.StatusUISpeciesRecord,
    viewModel: SpeciesRecordsViewModel,
    showRequestPermission: MutableState<Boolean>
) {

    //region progress
    if (stateUI.loading == SpeciesRecordsViewModel.StatusLoading.LOADING) {
        ProgressDialog()
    }
    //endregion

    //region showError
    if (stateUI.loading == SpeciesRecordsViewModel.StatusLoading.SHOW_ERROR) {
        ErrorDialogView(
            onDismiss = {
                viewModel.requestPermissionsStorage()
                showRequestPermission.value = true
            },
            exception = stateUI.logicException!!,
            onCancel =  {
                viewModel.requestPermissionsStorage()
                exitProcess(0)
            }
        )
    }
    //endregion

    //region request permission
    if(stateUI.loading == SpeciesRecordsViewModel.StatusLoading.REQUEST_PERMISSIONS_STORAGE) {
        EnablePermissionsNecessariesInConfigApp(
            showDialog = showRequestPermission,
            actionCancel = { exitProcess(0) },
            actionDismiss = { viewModel.requestPermissionsStorage()}
        )
    }
    //endregion
}
//endregion

//region counter
@Composable
fun HandlerCounterAndList(
    modifier: Modifier,
    stateUI: SpeciesRecordsViewModel.StatusUISpeciesRecord,
    navigateToRegisterNewInsect: () -> Unit
) {
    ConstraintLayout(modifier = modifier) {
        val (
            newCounterId,
            listCounters
        ) = createRefs()

        //region counter
        if(stateUI.list.isEmpty()) {

            //region New counter
            val guidelineNewCounterEnd = createGuidelineFromEnd(fraction = 0.03f)
            val guidelineNewCounterStart = createGuidelineFromStart(fraction = 0.03f)

            NewCounterView(modifier = Modifier
                .constrainAs(newCounterId) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(guidelineNewCounterEnd)
                    start.linkTo(guidelineNewCounterStart)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }.aspectRatio(4.75f),
                onClick = navigateToRegisterNewInsect
            )
            //endregion

        } else {
            //region New counter
            val guidelineNewCounterBottom = createGuidelineFromBottom(fraction = 0.74f)
            val guidelineNewCounterEnd = createGuidelineFromEnd(fraction = 0.03f)
            val guidelineNewCounterStart = createGuidelineFromStart(fraction = 0.03f)

            NewCounterView(modifier = Modifier
                .constrainAs(newCounterId) {
                    bottom.linkTo(guidelineNewCounterBottom)
                    end.linkTo(guidelineNewCounterEnd)
                    start.linkTo(guidelineNewCounterStart)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(4.75f),
                onClick = navigateToRegisterNewInsect
            )
            //endregion

            //region listCounter
            val guidelineListCountersBottom = createGuidelineFromBottom(fraction = 0.07f)
            val guidelineListCountersEnd = createGuidelineFromEnd(fraction = 0.07f)
            val guidelineListCountersStart = createGuidelineFromStart(fraction = 0.07f)
            val guidelineListCountersTop = createGuidelineFromTop(fraction = 0.28f)
            LazyColumn(modifier = Modifier
                .constrainAs(listCounters) {
                    bottom.linkTo(guidelineListCountersBottom)
                    end.linkTo(guidelineListCountersEnd)
                    start.linkTo(guidelineListCountersStart)
                    top.linkTo(guidelineListCountersTop)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
            ){
                items(count = stateUI.list.size, itemContent = {
                    ItemListCounterRecord(modifier = Modifier.padding(bottom = 15.dp), counter = stateUI.list[it])
                })
            }
            //endregion

        }

        //endregion

    }
}
//endregion

@Composable
fun HandlerButtons(
    modifier: Modifier,
    statusUI: SpeciesRecordsViewModel.StatusUISpeciesRecord,
    navigateToResume: () -> Unit,
    viewModel: SpeciesRecordsViewModel
) {
    if(statusUI.list.isEmpty()) return
    ConstraintLayout(modifier = modifier) {
        val (buttonInformes, buttonRegisters) = createRefs()
        CustomButtomOutline(
            modifier = Modifier.constrainAs(buttonInformes) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            },
            text = stringResource(id = R.string.informes),
            onClick = navigateToResume
        )
        CustomRoundedButtonsWithElevation(
            modifier = Modifier.constrainAs(buttonRegisters) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            },
            text = stringResource(id = R.string.registros),
            onClick = { viewModel.loadListCounters() }
        )
    }
}

//endregion


//region logic composables
private fun logicView(
    statusUI: SpeciesRecordsViewModel.StatusUISpeciesRecord,
    viewModel: SpeciesRecordsViewModel,
    stateActivity : MutableState<ActivityState>,
) {
    handlerStateComposable(statusUI = statusUI, viewModel = viewModel)
    handlerStateComposableBasedInActivity(
        stateActivity = stateActivity,
        viewModel = viewModel
    )
}

private fun handlerStateComposable(
    statusUI: SpeciesRecordsViewModel.StatusUISpeciesRecord,
    viewModel: SpeciesRecordsViewModel
) {

    if (statusUI.loading == SpeciesRecordsViewModel.StatusLoading.PRELOAD) {
        viewModel.loadListCounters()
        return
    }
    println(" state: ${statusUI.loading}")

}

private fun handlerStateComposableBasedInActivity(
    stateActivity : MutableState<ActivityState>,
    viewModel: SpeciesRecordsViewModel,
) {
    if(stateActivity.value != ActivityState.PAUSE) return
    viewModel.changePreloadStatus()
}
//endregion

//region preview
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
//@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun SpeciesRecordsViewPreview() {
    EntomologoTheme {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val constraintsId = createRef()
            val stateActivity : MutableState<ActivityState> = remember { mutableStateOf(value = ActivityState.RESUME) }
            //region list counters
            val list = emptyList<CounterRecordInsectModel>().toMutableList()
            for(counter in 1..10) {
                list.add(
                    CounterRecordInsectModel(
                        id= counter.toLong(),
                        insect = InsectModel( specieName = "Hormiga $counter", urlPhoto = "", moreInformation = "aqui $counter"),
                        geoLocation = GeoLocationModel(lat = 1.11, lng = 2.22, city = ""),
                        comment = "Un comentario $counter",
                        count = counter,
                    )
                )
            }
            //endregion
            val uiStateImageProfile = MutableStateFlow(value = ImageProfileViewModel.ImageProfileUIState())

            SpeciesRecordsView(modifier = Modifier.constrainAs(constraintsId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
                viewModel = object : SpeciesRecordsViewModel() {
                    override fun requestPermissionsStorage() {Log.i("err", "")}
                    override fun changePreloadStatus() {Log.i("err", "")}

                    override fun loadListCounters() {
                        Log.i("err", "")
                        viewModelScope.launch(Dispatchers.IO) {
                            updateStateUI(
                                list = emptyList()
                            )
                        }
                    }
                },
                imageProfileViewModel = object : ImageProfileViewModel() {
                    override fun loadImage() {
                        Log.i("Informacion", "accion")
                    }

                    override fun setImageSelected(bitmap: Bitmap?) {
                        Log.i("Informacion", "accion")
                    }

                    override fun getStateUI(): StateFlow<ImageProfileUIState> = uiStateImageProfile
                },
                navigateToImageProfile = {},
                navigateToRegisterNewInsect = {},
                stateActivity = stateActivity,
                navigateToReports = {}
            )
        }
    }
}
//endregion
