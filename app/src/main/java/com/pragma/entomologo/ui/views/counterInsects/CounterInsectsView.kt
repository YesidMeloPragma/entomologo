package com.pragma.entomologo.ui.views.counterInsects

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.pragma.entomologo.R
import com.pragma.entomologo.logic.excepciones.LogicException
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.ui.dialogs.errorDialog.ErrorDialogView
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.utils.extentions.getBitmap
import com.pragma.entomologo.ui.views.counterInsects.viewModel.CounterInsectsViewModel
import com.pragma.entomologo.ui.views.counterInsects.viewModel.CounterInsectsViewModelImpl
import com.pragma.entomologo.ui.views.customs.buttons.ButtonAdd
import com.pragma.entomologo.ui.views.customs.buttons.ButtonRemove
import com.pragma.entomologo.ui.views.customs.buttons.CustomRoundedButtonsWithElevation
import com.pragma.entomologo.ui.views.customs.dialogs.progressDialog.ProgressDialog
import com.pragma.entomologo.ui.views.customs.texts.CustomTextArea

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PHONE
)
@Composable
fun CounterInsectsViewPreview() {
    EntomologoTheme {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            val constraintsId = createRef()
            CounterInsectsView(modifier = Modifier.constrainAs(constraintsId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            },
                viewModel = object : CounterInsectsViewModel() {
                    override fun loadCounterInsects(counterInsect: Int, insectId: Int) {
                        Log.i("Informacion", "Elementos")
                    }

                    override fun removeInsectFromCounter() {
                        Log.i("Informacion", "Elementos")
                    }

                    override fun saveRecord(comment: String) {
                        Log.i("Informacion", "Elementos")
                    }

                    override fun updateState(
                        counter: Int,
                        imageBase64: String?,
                        insectModel: InsectModel?,
                        statesCounterInsectsUI: StatesCounterInsectsUI,
                        throwable: Throwable?
                    ) {
                        Log.i("Informacion", "Elementos")
                    }

                    override fun addInsectToCounter() {
                        Log.i("Informacion", "Elementos")
                    }

                    override fun closeException() {
                        Log.i("Informacion", "Elementos")
                    }

                },
                navigateToList = {},
            )
        }
    }
}

@Composable
fun CounterInsectsView(
    modifier: Modifier = Modifier,
    insectId: Int = -1,
    counterInsect: Int = -1,
    viewModel: CounterInsectsViewModel = hiltViewModel<CounterInsectsViewModelImpl>(),
    navigateToList: () -> Unit
) {
    //region stateObserver
    val currentState by viewModel.uiState.collectAsState(initial = CounterInsectsViewModel.CounterInsectsUIState())
    logicApplyInView(
        currentState = currentState,
        insectId = insectId,
        counterInsect = counterInsect,
        navigateToList= navigateToList,
        viewModel = viewModel
    )
    //endregion

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (headerId, bodyId) = createRefs()
        Header(
            modifier = Modifier.constrainAs(headerId) {},
            currentState = currentState,
            viewModel = viewModel
        )
        Body(
            modifier = Modifier.constrainAs(bodyId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
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
    currentState: CounterInsectsViewModel.CounterInsectsUIState,
    viewModel: CounterInsectsViewModel
) {
    ConstraintLayout(modifier = modifier) {
        if (currentState.statesCounterInsectsUI == CounterInsectsViewModel.StatesCounterInsectsUI.LOADING)  {
            ProgressDialog()
        }
        if (currentState.statesCounterInsectsUI == CounterInsectsViewModel.StatesCounterInsectsUI.SHOW_ERROR) {
            ErrorDialogView(
                onDismiss = {
                    viewModel.closeException()
                },
                exception = currentState.throwable!! as LogicException
            )
        }
    }
}

@Composable
fun Body(
    modifier: Modifier,
    currentState: CounterInsectsViewModel.CounterInsectsUIState,
    viewModel: CounterInsectsViewModel
) {
    val observation = remember{ mutableStateOf("") }

    ConstraintLayout(modifier = modifier) {
        val (
            counterId,
            handlerId,
            observationId,
            saveButtonId
        ) = createRefs()

        //region counter
        val separationHorizontal = 0.38f
        val guidelineBottomCounter = createGuidelineFromBottom(0.68f)
        val guidelineEndCounter = createGuidelineFromEnd(separationHorizontal)
        val guidelineStartCounter = createGuidelineFromStart(separationHorizontal)
        val guidelineTopCounter = createGuidelineFromTop(0.23f)
        Counter(
            modifier = Modifier.constrainAs(counterId) {
                bottom.linkTo(guidelineBottomCounter)
                end.linkTo(guidelineEndCounter)
                start.linkTo(guidelineStartCounter)
                top.linkTo(guidelineTopCounter)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
            currentState = currentState
        )
        //endregion

        //region handler counter
        val separationHorizontalHandlerCounter = 0.039f
        val guidelineEndHandlerCounter = createGuidelineFromEnd(separationHorizontalHandlerCounter)
        val guidelineStartHandlerCounter = createGuidelineFromStart(separationHorizontalHandlerCounter)
        val guidelineTopHandlerCounter = createGuidelineFromTop(0.33f)
        HandlerCounter(
            modifier = Modifier.constrainAs(handlerId) {
                end.linkTo(guidelineEndHandlerCounter)
                start.linkTo(guidelineStartHandlerCounter)
                top.linkTo(guidelineTopHandlerCounter, margin = 10.dp)
                width = Dimension.fillToConstraints
            },
            currentState = currentState,
            viewModel = viewModel
        )
        //endregion

        //region Observation
        val separationObservation = 0.04f
        val guidelineBottomObservation = createGuidelineFromBottom(0.32f)
        val guidelineEndObservation = createGuidelineFromEnd(separationObservation)
        val guidelineStartObservation = createGuidelineFromStart(separationObservation)
        val guidelineTopObservation = createGuidelineFromTop(0.47f)
        Observation(
            modifier = Modifier.constrainAs(observationId){
                bottom.linkTo(guidelineBottomObservation)
                end.linkTo(guidelineEndObservation)
                start.linkTo(guidelineStartObservation)
                top.linkTo(guidelineTopObservation)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            },
            currentState = currentState,
            observation = observation
        )
        //endregion

        //region save
        SaveButton(
            modifier = Modifier
                .constrainAs(saveButtonId) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(observationId.bottom)
                }
            ,
            currentState = currentState,
            observation = observation,
            viewModel = viewModel
        )
        //endregion
    }
}

//region counter
@Composable
fun Counter(
    modifier: Modifier,
    currentState: CounterInsectsViewModel.CounterInsectsUIState
) {
    ConstraintLayout(modifier = modifier
        .background(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(size = 8.dp)
        )
    ) {
        val textId = createRef()
        Text(
            text = if(currentState.counter < 10) "0${currentState.counter}" else currentState.counter.toString(),
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 55.sp),
            modifier = Modifier.constrainAs(textId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
        )
    }
}
//endregion

//region handler counter
@Composable
fun HandlerCounter(
    modifier: Modifier,
    currentState: CounterInsectsViewModel.CounterInsectsUIState,
    viewModel: CounterInsectsViewModel
) {

    ConstraintLayout(modifier = modifier
        .background(color = MaterialTheme.colorScheme.surface)
        .aspectRatio(4.75f)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f))
        ) {
            val (
                addId ,
                removeId,
                imageInsectId,
                nameInsectId
            ) = createRefs()

            //region button add
            val separationVerticalButtonAdd = 0.12f
            val guidelineBottomAddButton = createGuidelineFromBottom(separationVerticalButtonAdd)
            val guidelineEndAddButton = createGuidelineFromEnd(0.04f)
            val guidelineTopAddButton = createGuidelineFromTop(separationVerticalButtonAdd)
            ButtonAdd(
                modifier = Modifier.constrainAs(addId) {
                    bottom.linkTo(guidelineBottomAddButton)
                    end.linkTo(guidelineEndAddButton)
                    top.linkTo(guidelineTopAddButton)
                    height = Dimension.fillToConstraints
                },
                onClick = viewModel::addInsectToCounter
            )
            //endregion

            //region button remove
            val separationVerticalButtonRemove = 0.19f
            val guidelineBottomRemoveButton = createGuidelineFromBottom(separationVerticalButtonRemove)
            val guidelineEndRemoveButton = createGuidelineFromEnd(0.31f)
            val guidelineStartRemoveButton = createGuidelineFromStart(0.54f)
            val guidelineTopRemoveButton = createGuidelineFromTop(separationVerticalButtonRemove)
            ButtonRemove(
                modifier = Modifier.constrainAs(removeId) {
                    bottom.linkTo(guidelineBottomRemoveButton)
                    end.linkTo(guidelineEndRemoveButton)
                    start.linkTo(guidelineStartRemoveButton)
                    top.linkTo(guidelineTopRemoveButton)
                    height = Dimension.fillToConstraints
                }.aspectRatio(1f),
                onClick = viewModel::removeInsectFromCounter
            )
            //endregion

            //region detail insect
            val guidelineBottomImageInsect = createGuidelineFromBottom(0.2f)
            val guidelineEndImageInsect = createGuidelineFromEnd(0.84f)
            val guidelineStartImageInsect = createGuidelineFromStart(0.03f)
            val guidelineTopImageInsect = createGuidelineFromTop(0.2f)
            ImageInsect(
                modifier = Modifier.constrainAs(imageInsectId) {
                    bottom.linkTo(guidelineBottomImageInsect)
                    end.linkTo(guidelineEndImageInsect)
                    start.linkTo(guidelineStartImageInsect)
                    top.linkTo(guidelineTopImageInsect)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
                currentState = currentState
            )
            //endregion

            //region name insect
            NameInsect(
                modifier = Modifier.constrainAs(nameInsectId){
                    bottom.linkTo(parent.bottom)
                    start.linkTo(imageInsectId.end, margin = 10.dp)
                    top.linkTo(parent.top)
                },
                currentState = currentState
            )
            //endregion
        }
    }
}

@Composable
fun NameInsect(
    modifier: Modifier,
    currentState: CounterInsectsViewModel.CounterInsectsUIState
) {
    Text(
        text = currentState.insectModel?.specieName?: stringResource(id = R.string.name_specie),
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Composable
fun ImageInsect(
    modifier: Modifier,
    currentState: CounterInsectsViewModel.CounterInsectsUIState
) {
    ConstraintLayout(modifier = modifier) {
        val (placeholerId, imageElementId) = createRefs()
        if(currentState.imageBase64 == null) {
            Image(
                painter = painterResource(id = R.drawable.scorpion),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(placeholerId) {}
            )
        } else {
            Image(
                bitmap = currentState.imageBase64.getBitmap().asImageBitmap(),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(imageElementId) {}
            )
        }
    }
}
//endregion

//region observation
@Composable
fun Observation(
    modifier: Modifier,
    currentState: CounterInsectsViewModel.CounterInsectsUIState,
    observation: MutableState<String>
) {
    if(currentState.counter <= 0) {
        observation.value = ""
        return
    }


    CustomTextArea(
        modifier = modifier,
        value = observation.value,
        onValueChange = { observation.value = it },
        placeHolderText = stringResource(id = R.string.add_comment)
    )
}
//endregion

//region save button
@Composable
fun SaveButton(
    modifier: Modifier,
    currentState: CounterInsectsViewModel.CounterInsectsUIState,
    observation: MutableState<String>,
    viewModel: CounterInsectsViewModel
) {
    if(currentState.counter <= 0) return
    if (currentState.statesCounterInsectsUI == CounterInsectsViewModel.StatesCounterInsectsUI.NAVIGATE_TO_LIST) return
    CustomRoundedButtonsWithElevation(
        modifier = modifier,
        text = stringResource(id = R.string.save),
        onClick = {viewModel.saveRecord(comment = observation.value)}
    )
}
//endregion

//endregion

//region logic
private fun logicApplyInView(
    currentState: CounterInsectsViewModel.CounterInsectsUIState,
    counterInsect: Int,
    insectId: Int,
    navigateToList: () -> Unit,
    viewModel: CounterInsectsViewModel
) {
    checkStatusUI(
        currentState = currentState,
        counterInsect = counterInsect,
        insectId = insectId,
        navigateToList = navigateToList,
        viewModel = viewModel
    )
}

private fun checkStatusUI(
    currentState: CounterInsectsViewModel.CounterInsectsUIState,
    counterInsect: Int,
    insectId: Int,
    navigateToList: () -> Unit,
    viewModel: CounterInsectsViewModel
) {
    if(currentState.statesCounterInsectsUI == CounterInsectsViewModel.StatesCounterInsectsUI.START) {
        viewModel.loadCounterInsects(counterInsect = counterInsect, insectId = insectId)
        return
    }

    if(currentState.statesCounterInsectsUI == CounterInsectsViewModel.StatesCounterInsectsUI.NAVIGATE_TO_LIST) {
        navigateToList.invoke()
        return
    }
}
//endregion