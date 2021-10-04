package com.shaun.newsbreeze.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shaun.newsbreeze.ui.theme.LightGreen

@Preview(showBackground = true)
@Composable
fun BreezeButton(
    title: String = "Read",
    onClick: () -> Unit = {},
    background: Color = LightGreen
) {

    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(15),
        modifier = Modifier
            .width(120.dp)
            .height(35.dp),

        ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(background), contentAlignment = Alignment.Center
        ) {
            Text(text = title)
        }
    }
}