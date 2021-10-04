package com.shaun.newsbreeze.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shaun.newsbreeze.ui.theme.LightGreen

@Preview
@Composable
fun HeaderSavedScreen(time: String = "Today", onSeeMoreClicked: () -> Unit = {}) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 20.dp)
    ) {
        Text(
            text = time,
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier.weight(0.4f)
        )
        Text(
            text = "See More..",
            color = LightGreen,
            fontSize = 14.sp,
            modifier = Modifier
                .clickable {
                    onSeeMoreClicked()
                }
                .fillMaxWidth(0.6f)
                .weight(0.7f),
            textAlign = TextAlign.End
        )
    }
}