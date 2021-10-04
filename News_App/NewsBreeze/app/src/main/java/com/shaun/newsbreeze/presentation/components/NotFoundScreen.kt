package com.shaun.newsbreeze.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.shaun.newsbreeze.ui.theme.HeadingFont

@Composable
fun NotFoundScreen() {
    Column(
        Modifier
            .fillMaxSize()

    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
           Text(text = "Oops!No Results Found",fontFamily = HeadingFont)
        }
    }
}