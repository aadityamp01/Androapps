package com.shaun.newsbreeze.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.shaun.newsbreeze.R
import com.shaun.newsbreeze.ui.theme.LightGreen
import com.shaun.newsbreeze.utils.AppConstants


@ExperimentalMaterialApi
@Preview
@Composable
fun HomeNewsItem(
    imageUrl: String? = AppConstants.dummyImage,
    title: String = "Lorem ipsum dolor sit amet sectetur adi",
    shortDescription: String? = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusan ium doloremque laudan...",
    date: String = "29-03-01",
    isSaved: Boolean = false,
    onReadClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onDelete: () -> Unit = {}
) {

    var saved by remember {
        mutableStateOf(false)
    }

    if (title.isEmpty() || shortDescription.isNullOrEmpty())
        return
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 35.dp, end = 35.dp)

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .alpha(0.2f),
                shape = RoundedCornerShape(40)
            ) {
                Column(
                    Modifier
                        .background(Color.LightGray)
                        .fillMaxSize()
                        .height(5.dp)
                        .padding(start = 30.dp)

                ) {

                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            onClick = { onReadClick() },
            shape = RoundedCornerShape(10)

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd,
            ) {

                val painter = rememberImagePainter(imageUrl, builder = {
                    crossfade(true)
                })

                Image(
                    painter = painter, contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxSize(1f),
                    contentScale = ContentScale.FillWidth

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
                                .align(Alignment.Center)
                                .height(200.dp)
                                .width(300.dp)
                        )
                    }
                }

                Column(Modifier.padding(top = 10.dp, end = 20.dp)) {
                    Card(
                        onClick = { if (isSaved) onDelete() else onSaveClick() },
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
                                painter = if (isSaved) painterResource(id = R.drawable.ic_saved) else painterResource(
                                    id = R.drawable.ic_not_saved
                                ),
                                contentDescription = "",
                                alignment = Alignment.TopCenter,
                                modifier = Modifier
                                    .size(22.dp)
                            )

                        }
                    }
                }
            }
        }

        Text(
            text = title, color = Color.Black,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,

            )
        Text(
            text = shortDescription ?: "",
            style = MaterialTheme.typography.h6.copy(
                color = Color.Black,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.clickable {
                onReadClick()
            }
        )

        Text(text = date, color = Color.Gray)

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp)
        ) {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                BreezeButton(title = "Read", onClick = {
                    onReadClick()
                })
                Spacer(modifier = Modifier.width(40.dp))
                BreezeButton(title = "Save", onClick = {
                    if (isSaved) {
                        onDelete()
                    } else
                        onSaveClick()
                })
            }
        }


    }
}