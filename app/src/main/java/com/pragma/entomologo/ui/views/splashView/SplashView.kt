package com.pragma.entomologo.ui.views.splashView

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.pragma.entomologo.R
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.views.splashView.viewModel.SplashViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private var startNavigation = true
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun SplashViewPreview() {
    EntomologoTheme {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val constraintsId = createRef()
            val loadingLiveData = MutableStateFlow(SplashViewModel.StateUISplash())
            SplashView(modifier = Modifier.constrainAs(constraintsId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
                viewModel = object: SplashViewModel() {
                    override fun finishLoadData() { Log.i("info", "finish load data") }
                    override fun load() { Log.i("info", "Elementos") }
                    override fun loadingStatus(): StateFlow<StateUISplash> = loadingLiveData
                },
                navigateToListRecords = {},
                navigateToRegisterEntomologist = {}
            )
        }
    }
}

@Composable
fun SplashView(
    modifier: Modifier,
    viewModel: SplashViewModel,
    navigateToListRecords: ()->Unit,
    navigateToRegisterEntomologist: () -> Unit
) {

    //region variables

    val loading by viewModel.loadingStatus().collectAsState()
    val compositionLottie by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.bees))
    val progressLottie by animateLottieCompositionAsState(composition = compositionLottie, iterations = LottieConstants.IterateForever)

    logicUI(
        loading = loading,
        viewModel = viewModel,
        navigateToListRecords = navigateToListRecords,
        navigateToRegisterEntomologist = navigateToRegisterEntomologist
    )
    //endregion

    ConstraintLayout(modifier = modifier.background(color = MaterialTheme.colorScheme.secondaryContainer)) {
        val (imageId, lottieId) = createRefs()

        //region logo app
        Image(
            painter = painterResource(id = R.mipmap.logo_entomology_1),
            contentDescription = "",
            modifier = Modifier.constrainAs(imageId){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
        )
        //endregion

        //region lottie
        val guidelineBottom = createGuidelineFromBottom(0.29f)
        LottieAnimation(
            composition = compositionLottie,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .constrainAs(lottieId) {
                    bottom.linkTo(guidelineBottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
            progress = { progressLottie }
        )
        //endregion

    }
}

private fun logicUI(
    loading: SplashViewModel.StateUISplash,
    viewModel: SplashViewModel,
    navigateToListRecords: ()->Unit,
    navigateToRegisterEntomologist: () -> Unit
) {
    when(loading.statusLoading) {
        SplashViewModel.StatusLoading.PRELOAD -> viewModel.load()
        SplashViewModel.StatusLoading.LOADING -> { startNavigation = true }
        SplashViewModel.StatusLoading.NAVIGATE_TO_LIST_RECORDS -> {
            viewModel.finishLoadData()
            if (startNavigation) {
                startNavigation = false
                navigateToListRecords.invoke()
            }
        }
        SplashViewModel.StatusLoading.NAVIGATE_TO_REGISTER_ENTOMOLOGIST -> {
            viewModel.finishLoadData()
            if (startNavigation) {
                startNavigation = false
                navigateToRegisterEntomologist.invoke()
            }
        }
        SplashViewModel.StatusLoading.FINISH_LOAD -> {
            startNavigation = true
        }
    }
}