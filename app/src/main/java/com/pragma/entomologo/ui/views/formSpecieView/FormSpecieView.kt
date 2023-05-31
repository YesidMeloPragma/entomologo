package com.pragma.entomologo.ui.views.formSpecieView

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.pragma.entomologo.R
import com.pragma.entomologo.logic.models.InsectModel
import com.pragma.entomologo.ui.navigation.Routes
import com.pragma.entomologo.ui.theme.EntomologoTheme
import com.pragma.entomologo.ui.views.customs.buttons.CustomRoundedButtonsWithElevation
import com.pragma.entomologo.ui.views.customs.dialogs.progressDialog.ProgressDialog
import com.pragma.entomologo.ui.views.customs.images.CustomCircularImage
import com.pragma.entomologo.ui.views.customs.texts.CustomAutocompleteText
import com.pragma.entomologo.ui.views.customs.texts.CustomTextField
import com.pragma.entomologo.ui.views.formSpecieView.viewmodel.FormSpecieViewModel

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun FormSpecieViewPreview() {
    EntomologoTheme {
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)) {

            val constraintsId = createRef()
            val livedataListInsect = MutableLiveData<List<InsectModel>>()
            val livedateProgress = MutableLiveData<Boolean>()
            val currentSpecieName = MutableLiveData<String>()
            val currentMoreInformation = MutableLiveData<String>()
            val currentUrlPhoto = MutableLiveData<String>()
            val pairNavigation = MutableLiveData<Pair<Boolean, InsectModel>>()

            FormSpecieView(
                modifier = Modifier.constrainAs(constraintsId) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            },
                viewModel = object : FormSpecieViewModel() {
                    override fun goToSpeciesRegisterRecords(): LiveData<Pair<Boolean, InsectModel>> = pairNavigation
                    override fun listInsects(): LiveData<List<InsectModel>> = livedataListInsect
                    override fun loading(): LiveData<Boolean> = livedateProgress
                    override fun loadListInsects() { Log.i("FormSpecie", "Cargo la lista de insectos")}
                    override fun saveInsect() { Log.i("FormSpecie", "Guardado la informacion")}
                    override fun setInsectSelected(insectModel: InsectModel) { Log.i("FormSpecie", "Guardado la informacion") }
                    override fun setTextAutocomplete(text: String) { currentSpecieName.postValue(text) }
                    override fun setTextMoreInformation(text: String) { currentMoreInformation.postValue(text) }
                    override fun setTextUrlPhoto(text: String) { currentUrlPhoto.postValue(text) }
                    override fun textAutocomplete(): LiveData<String> = currentSpecieName
                    override fun textMoreInformation(): LiveData<String> = currentMoreInformation
                    override fun textUrlPhoto(): LiveData<String> = currentUrlPhoto
                }
            )
        }
    }
}

@Composable
fun FormSpecieView(
    modifier: Modifier,
    viewModel: FormSpecieViewModel,
    navHostController: NavHostController? = null
) {

    //region values
    viewModel.loadListInsects()
    val loading : Boolean by viewModel.loading().observeAsState(initial = false)
    val listItems : List<InsectModel> by viewModel.listInsects().observeAsState(initial = emptyList())
    val textAutcomplete : String by viewModel.textAutocomplete().observeAsState(initial = "")
    val textMoreInformation : String by viewModel.textMoreInformation().observeAsState(initial = "")
    val goToSpeciesRecords : Pair<Boolean, InsectModel> by viewModel.goToSpeciesRegisterRecords().observeAsState(
        initial = Pair(first = false, second = InsectModel(specieName = "", urlPhoto = "", moreInformation = ""))
    )
    //endregion

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (
            imageProfileId,
            specieImageId,
            autocompleteId,
            additionalInformationId,
            selectButtonId
        ) = createRefs()

        //region profileImage
        val guidelineImageProfileEnd = createGuidelineFromEnd(fraction = 0.78f)
        val guidelineImageProfileStart = createGuidelineFromStart(fraction = 0.06f)
        val guidelineImageProfileTop = createGuidelineFromTop(fraction = 0.07f)

        CustomCircularImage(
            modifier = Modifier.constrainAs(imageProfileId) {
                end.linkTo(guidelineImageProfileEnd)
                start.linkTo(guidelineImageProfileStart)
                top.linkTo(guidelineImageProfileTop)
                width = Dimension.fillToConstraints
            }, placeHolder = R.mipmap.avatar)
        //endregion

        //region form

        //region imageSpecie
        val guidelineImageSpecieEnd = createGuidelineFromEnd(fraction = 0.35f)
        val guidelineImageSpecieStart = createGuidelineFromStart(fraction = 0.35f)
        val guidelineImageSpecieTop = createGuidelineFromTop(fraction = 0.21f)

        CustomCircularImage(
            modifier = Modifier.constrainAs(specieImageId) {
                end.linkTo(guidelineImageSpecieEnd)
                start.linkTo(guidelineImageSpecieStart)
                top.linkTo(guidelineImageSpecieTop)
                width = Dimension.fillToConstraints
            },
            placeHolder = R.mipmap.avatar,
            widthBorder = 6.dp,
            colorBorder = MaterialTheme.colorScheme.primaryContainer
        )
        //endregion

        //region autocomplete
        val guidelineAutoCompleteEnd = createGuidelineFromEnd(fraction = 0.34f)
        val guidelineAutoCompleteStart = createGuidelineFromStart(fraction = 0.17f)
        val guidelineAutoCompleteTop = createGuidelineFromTop(fraction = 0.43f)

        CustomAutocompleteText(
            modifier = Modifier.constrainAs(autocompleteId){
                end.linkTo(guidelineAutoCompleteEnd)
                start.linkTo(guidelineAutoCompleteStart)
                top.linkTo(guidelineAutoCompleteTop)
                width = Dimension.fillToConstraints
            },
            list = listItems,
            onValueChanged = viewModel::setTextAutocomplete,
            itemSelected = { viewModel.setInsectSelected(insectModel = it)  },
            label = "Especie"
        )

        //endregion

        //region additional information
        val guideLineAddInfoEnd = createGuidelineFromEnd(fraction = 0.10f)
        val guideLineAddInfoStart = createGuidelineFromStart(fraction = 0.10f)
        val guideLineAddInfoTop = createGuidelineFromTop(fraction = 0.52f)

        CustomTextField(
            modifier =Modifier.constrainAs(additionalInformationId){
                end.linkTo(guideLineAddInfoEnd)
                start.linkTo(guideLineAddInfoStart)
                top.linkTo(guideLineAddInfoTop)
                width = Dimension.fillToConstraints
            },
            value = textMoreInformation,
            onValueChange = {  viewModel.setTextMoreInformation(text = it)  },
            label = "Informacion Adicional"
        )
        //endregion

        //region select button
        if(textAutcomplete.isNotEmpty() && textMoreInformation.isNotEmpty()) {
            val guidelineSelectButtonBottom = createGuidelineFromBottom(fraction = 0.14f)
            val guidelineSelectButtonEnd = createGuidelineFromEnd(fraction = 0.35f)
            val guidelineSelectButtonStart = createGuidelineFromStart(fraction = 0.35f)

            CustomRoundedButtonsWithElevation(
                modifier = Modifier.constrainAs(selectButtonId){
                    bottom.linkTo(guidelineSelectButtonBottom)
                    end.linkTo(guidelineSelectButtonEnd)
                    start.linkTo(guidelineSelectButtonStart)
                },
                text = "Seleccionar",
                onClick = viewModel::saveInsect
            )
        }
        //endregion

        if (loading) {
            ProgressDialog()
        }

        if(goToSpeciesRecords.first) {
            navHostController?.navigate(Routes.REGISTER_NEW_INSECT.route)
        }
        //endregion

    }
}