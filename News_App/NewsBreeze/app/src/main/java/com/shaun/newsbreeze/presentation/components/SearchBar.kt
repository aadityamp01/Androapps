package com.shaun.newsbreeze.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.shaun.newsbreeze.R
import com.shaun.newsbreeze.viewmodels.HomeViewModel

@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    viewModel: HomeViewModel,
    onSort: () -> Unit = {},
    onExecuteSearch: (String) -> Unit = {},

    ) {
    val inputvalue: String? by viewModel.searchStringLiveData.observeAsState(initial = "")


    val keyboardController = LocalSoftwareKeyboardController.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp),

        shape = RoundedCornerShape(35)
    ) {
        Column(Modifier.background(Color.White)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp),
                    value = inputvalue ?: "",
                    onValueChange = {
                        viewModel.onQuery(it)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onExecuteSearch(inputvalue ?: "")
//              focusManager.clearFocus(forcedClear = true) // close keyboard
                            keyboardController?.hideSoftwareKeyboard() // another way to close keyboard
                        },
                    ),
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Gray,
                        disabledTextColor = Color.Transparent,
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(text = "Search")
                    }, trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort),
                            contentDescription = "Search Icon",
                            Modifier
                                .alpha(.5f)
                                .clickable {
                                    onSort()
                                }
                                .size(30.dp), tint = Color.LightGray
                        )
                    },
                    shape = CircleShape
                )


            }

        }
    }
}