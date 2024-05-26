package com.aaditya.composesharedelementtransition

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ListScreen (
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (Int, String) -> Unit
) {
    val drawables = listOf(
        R.drawable.img1,
        R.drawable.img2,
    )

    val names = listOf(
        "Friends",
        "BBT"
    )

    LazyColumn (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(drawables) { index, resId ->
            val text = "Series ${names[index]}"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(resId, text) }
            ) {
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(16 / 9f)
                        .weight(1f)
                        .sharedElement(
                            state = rememberSharedContentState(key = "image/$resId"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        )
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = text,
                    modifier = Modifier
                        .weight(1f)
                        .sharedElement(
                            state = rememberSharedContentState(key = "text/$text"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        )
                )
            }
        }
    }

}