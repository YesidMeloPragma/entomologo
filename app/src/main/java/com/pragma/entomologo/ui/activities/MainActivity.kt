package com.pragma.entomologo.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.pragma.entomologo.ui.navigation.NavigationHandler
import com.pragma.entomologo.ui.theme.EntomologoTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var viewModelHandler: ViewModelHandler
    private var stateActivity : MutableState<ActivityState>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EntomologoTheme {
                 stateActivity = remember{ mutableStateOf(ActivityState.RESUME) }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    NavigationHandler(
                        viewModelHandler = viewModelHandler,
                        stateActivity = stateActivity!!
                    )
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        stateActivity?.value = ActivityState.PAUSE
    }

    override fun onResume() {
        super.onResume()
        stateActivity?.value = ActivityState.RESUME
    }
}