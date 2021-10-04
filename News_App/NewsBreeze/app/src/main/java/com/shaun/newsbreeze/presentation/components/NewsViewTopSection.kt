package com.shaun.newsbreeze.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.shaun.newsbreeze.R
import com.shaun.newsbreeze.utils.AppConstants

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun NewsViewTopSection(
    imageUrl: String = AppConstants.dummyImage,

) {
    Box {
        val painter = rememberImagePainter(imageUrl, builder = {
            crossfade(true)
        })


        Image(
            painter = painter, contentDescription = null,

            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )

        when (painter.state) {


            is ImagePainter.State.Loading -> {

                ShimmerItem(
                    size = 200,
                    showBottomLine = false,
                    paddingStart = 0,
                    paddingEnd = 0,
                    paddingTop = 0
                )
            }
            is ImagePainter.State.Error -> {

                Image(
                    painter = painterResource(id = R.drawable.failed),
                    null,
                    Modifier
                        .height(400.dp)
                        .fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
        }

    }
}