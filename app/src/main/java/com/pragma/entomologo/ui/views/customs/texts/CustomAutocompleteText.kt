package com.pragma.entomologo.ui.views.customs.texts

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pragma.entomologo.ui.theme.EntomologoTheme

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PHONE)
@Composable
fun CustomAutocompleteTextPreview() {
    EntomologoTheme {
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            val (customRoundedId) = createRefs()
            val adapter = emptyArray<String>().toMutableList()
            adapter.add("Uno")
            adapter.add("Dos")
            adapter.add("Tres")
            adapter.add("Cuatro")

            CustomAutocompleteText(
                modifier = Modifier.constrainAs(customRoundedId){
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                    //height = Dimension.fillToConstraints
                },
                list = adapter,
                initialValue = adapter[0],
                itemSelected = {

                },
                onValueChanged = { },
                label = "Hola mundo"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T>CustomAutocompleteText(
    modifier: Modifier,
    initialValue: T? = null,
    list: List<T>,
    itemSelected: (T)->Unit,
    onValueChanged: (String)-> Unit,
    label : String
) {
    var expanded : Boolean by remember { mutableStateOf(false) }
    var listFiltered: List<T> by remember { mutableStateOf(list) }
    var currentValue: String  by remember { mutableStateOf(initialValue?.toString()?:"") }

    ExposedDropdownMenuBox(
        modifier= modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
            ,
            value = currentValue,
            maxLines = 1,
            onValueChange = { currentValueString ->
                currentValue = currentValueString
                if (currentValue.length % 3 != 0) {
                    return@OutlinedTextField
                }
                listFiltered = list.filter { it.toString().lowercase().startsWith(currentValue.lowercase()) }.toList().take(3)
                if (listFiltered.isEmpty()) {
                    expanded = false
                    onValueChanged.invoke(currentValue)
                    return@OutlinedTextField
                }
                expanded = true
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedIndicatorColor = MaterialTheme.colorScheme.background,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
                textColor = MaterialTheme.colorScheme.onSurface,
            ),
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.surface)
                        .padding(horizontal = 4.dp)
                )
            }
        )
        ExposedDropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = !expanded }
        ) {
            for(element in listFiltered) {
                DropdownMenuItem(
                    text = { Text(text = element.toString()) },
                    onClick = {
                        expanded = false
                        currentValue = element.toString()
                        itemSelected.invoke(element)
                    })
            }
        }
    }
}