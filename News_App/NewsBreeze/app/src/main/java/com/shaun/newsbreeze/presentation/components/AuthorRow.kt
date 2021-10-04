package com.shaun.newsbreeze.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.shaun.newsbreeze.utils.AppConstants

@Composable
fun AuthorRow(
    imageUrl: String = AppConstants.dummyImage,
    author: String,
    source: String,
    onSaveClick: () -> Unit,
    isSaved: Boolean,
    onDeleteClicked: () -> Unit

) {

    val buttonTitle = if (isSaved) "Unsave" else "Save"

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp),
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter( imageUrl),
                contentDescription = null,
                Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(text = author, color = Color.Black)
                Text(text = source, fontSize = 10.sp)
            }
        }

        BreezeButton(title = buttonTitle, onClick = {
            if (isSaved)
                onDeleteClicked()
            else
                onSaveClick()
        })
    }
}