package com.shaun.newsbreeze.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shaun.newsbreeze.R
import com.shaun.newsbreeze.ui.theme.HeadingFont
import com.shaun.newsbreeze.ui.theme.LightGreen

@ExperimentalMaterialApi

@Composable
fun Header(openSave:()->Unit) {

    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "News Breeze",
            fontFamily = HeadingFont,
            color = Color.Black,
            fontSize = 30.sp,

            )
        Card(
            onClick = {openSave() },
            modifier = Modifier
                .size(30.dp),
            shape = RoundedCornerShape(10),

            ) {

            Box(
                Modifier
                    .background(LightGreen)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_not_saved),
                    contentDescription = "",
                    alignment = Alignment.TopCenter,
                    modifier = Modifier
                        .size(22.dp)
                )

            }
        }
    }
}