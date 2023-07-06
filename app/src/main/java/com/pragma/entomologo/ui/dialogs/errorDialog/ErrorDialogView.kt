package com.pragma.entomologo.ui.dialogs.errorDialog

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.pragma.entomologo.R
import com.pragma.entomologo.logic.excepciones.LogicException
import com.pragma.entomologo.ui.dialogs.errorDialog.extentions.getMessage
import com.pragma.entomologo.ui.dialogs.errorDialog.extentions.getTitle
import com.pragma.entomologo.ui.dialogs.errorDialog.viewModel.ErrorDialogViewModel
import com.pragma.entomologo.ui.dialogs.errorDialog.viewModel.ErrorDialogViewModelImpl
import com.pragma.entomologo.ui.theme.EntomologoTheme

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PHONE
)
@Composable
fun ErrorDialogViewPreview() {
    EntomologoTheme {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            ErrorDialogView(
                viewModel = object : ErrorDialogViewModel() {
                    override fun loadErrorDialog() {
                        Log.i("Informacion", "Informacion")
                    }

                    override fun sendData() {
                        Log.i("Informacion", "Informacion")
                    }
                },
                onDismiss = {}
            )
        }
    }
}
//region elements
@Composable
fun ErrorDialogView(
    exception: LogicException = LogicException(),
    viewModel: ErrorDialogViewModel = hiltViewModel<ErrorDialogViewModelImpl>(),
    onDismiss: (Boolean) -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss.invoke(true) },
        content = {
            ConstraintLayout(modifier = Modifier.fillMaxSize()){
                val contentId = createRef()
                val guidelineBottom = createGuidelineFromBottom(0.4f)
                val guidelineEnd = createGuidelineFromEnd(0.06f)
                val guidelineStart = createGuidelineFromStart(0.06f)
                val guidelineTop = createGuidelineFromTop(0.4f)

                ContentDialog(
                    modifier = Modifier
                        .constrainAs(contentId) {
                            bottom.linkTo(guidelineBottom)
                            end.linkTo(guidelineEnd)
                            start.linkTo(guidelineStart)
                            top.linkTo(guidelineTop)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }
                        .background(color = MaterialTheme.colorScheme.background),
                    onDismiss = onDismiss,
                    exception = exception,
                    viewModel = viewModel
                )
            }
        }
    )
}

//region content
@Composable
fun ContentDialog(
    exception: LogicException,
    modifier: Modifier,
    onDismiss: (Boolean) -> Unit,
    viewModel: ErrorDialogViewModel
) {
    ConstraintLayout(modifier = modifier) {
        val (headerId, messageId, buttonId) = createRefs()
        Header(
            exception = exception,
            modifier = Modifier.constrainAs(headerId){
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            }
        )
        ButtonDialog(
            modifier = Modifier.constrainAs(buttonId){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                width = Dimension.fillToConstraints
            },
            onDismiss = onDismiss,
            viewModel = viewModel
        )
        Message(
            modifier = Modifier.constrainAs(messageId){
                bottom.linkTo(buttonId.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(headerId.bottom)
            },
            exception = exception
        )
    }
}

@Composable
fun Header(
    exception: LogicException,
    modifier: Modifier
) {
    ConstraintLayout(modifier = modifier
        .aspectRatio(7.4f)
        .background(color = MaterialTheme.colorScheme.onBackground)
    ) {
        val textId = createRef()
        Text(
            text = stringResource(id = exception.typeException.getTitle()),
            style = MaterialTheme.typography.headlineSmall.copy(
                color = MaterialTheme.colorScheme.background
            ),
            modifier = Modifier.constrainAs(textId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
        )
    }
}

@Composable
fun ButtonDialog(
    modifier: Modifier,
    onDismiss: (Boolean) -> Unit,
    viewModel: ErrorDialogViewModel
) {
    ConstraintLayout(modifier = modifier
        .aspectRatio(7.4f)
        .background(color = MaterialTheme.colorScheme.onBackground)
        .clickable {
            onDismiss.invoke(true)
            viewModel.sendData()
        }
    ) {
        val textId = createRef()
        Text(
            text = stringResource(id = R.string.accept),
            style = MaterialTheme.typography.headlineSmall.copy(
                color = MaterialTheme.colorScheme.background
            ),
            modifier = Modifier.constrainAs(textId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
        )
    }
}

@Composable
fun Message(
    modifier: Modifier,
    exception: LogicException,
) {
    ConstraintLayout(modifier = modifier) {
        val textId = createRef()
        Text(
            text = stringResource(id = exception.typeException.getMessage()),
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
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

//endregion
