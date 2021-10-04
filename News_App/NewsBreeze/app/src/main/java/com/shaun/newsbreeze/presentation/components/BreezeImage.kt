package com.shaun.newsbreeze.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shaun.newsbreeze.R

@Composable
fun BreezeImage(height:Int,imageUrl:String?) {

//    val painter = rememberim(imageUrl, fadeIn = true)
//
//    Image(
//        painter = painter, contentDescription = null,
//        modifier = Modifier
//            .height(height = height.dp)
//            .fillMaxSize(1f),
//        contentScale = ContentScale.FillWidth
//
//    )
//    when (painter.loadState) {
//        is ImageLoadState.Loading -> {
//
//            ShimmerItem(
//                size = 200,
//                showBottomLine = false,
//                paddingStart = 0,
//                paddingEnd = 0,
//                paddingTop = 0
//            )
//        }
//        is ImageLoadState.Error -> {
//
//            Image(
//                painter = painterResource(id = R.drawable.failed),
//                null,
//                Modifier.height(200.dp).fillMaxWidth()
//            )
//        }
//    }

}