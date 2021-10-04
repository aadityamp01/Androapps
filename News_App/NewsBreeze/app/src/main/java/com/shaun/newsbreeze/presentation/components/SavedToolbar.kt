package com.shaun.newsbreeze.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shaun.newsbreeze.R
import com.shaun.newsbreeze.ui.theme.LightGreen

@Composable
@Preview
fun SavedToolbar(onBackClick: () -> Unit = {}) {

    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

      Box {
          Column(modifier = Modifier
              .clickable {
                  Log.d("TAG", "NewsViewToolbar: Emiited")
                  onBackClick()
              }

          ) {
              Image(
                  painter = painterResource(id = R.drawable.ic_back),
                  contentDescription = "",
                  alignment = Alignment.Center,
                  modifier = Modifier
                      .size(30.dp),
                  colorFilter = ColorFilter.tint(Color.Black)

              )


          }



          Text(text = "Saved", color = LightGreen, fontSize = 25.sp,
              modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
      }

    }
}